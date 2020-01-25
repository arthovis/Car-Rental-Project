package com.sda10.carrental.service;

import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.BookingRepository;
import com.sda10.carrental.repository.CarRepository;
import com.sda10.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public CarRepository carRepository;

    @Transactional
    public Booking createBooking(Customer client, Car car, LocalDate rentalDate, LocalDate returnDate, Branch rentalBranch, Branch returnBranch) {
        if (car.getStatus() != Status.AVAILABLE) {
            throw new RuntimeException("Car is not available.");
        }
        if (!rentalDate.isBefore(returnDate)) {
            throw new IllegalArgumentException();
        }
        Booking booking = buildBooking(client, car, rentalDate, returnDate, rentalBranch, returnBranch);
        return bookingRepository.save(booking);
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.getOne(id);
    }

    public Booking updateBooking(Long id, Booking booking) {
        Optional<Booking> bookingToUpdate = bookingRepository.findById(id);

        if (bookingToUpdate.isPresent()) {
            booking.setId(id);
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking could not be updated");
        }
    }

    public void deleteBooking(Long id) {
        Booking bookingToDelete = bookingRepository.findById(id).get();

        bookingRepository.delete(bookingToDelete);
    }

    public Booking buildBooking(Customer client, Car desiredCar, LocalDate rentalDate, LocalDate returnDate, Branch rentalBranch, Branch returnBranch) {

        Double amount = getCalculatedBookingAmount(desiredCar, rentalDate, returnDate);

        Customer customer = customerRepository.findById(client.getId()).get();

        Car car = carRepository.findById(desiredCar.getId()).get();

        Booking booking = new Booking();
        booking.setDateOfBooking(LocalDate.now());
        booking.setDateFrom(rentalDate);
        booking.setDateTo(returnDate);
        booking.setClient(customer);
        booking.setCar(car);
        booking.setRentalBranch(rentalBranch);
        booking.setReturnBranch(returnBranch);
        booking.addRental(rentalDate, rentalBranch);
        booking.addReturn(returnDate, returnBranch);
        booking.setAmount(amount);
        booking.setBookingStatus(BookingStatus.OPEN);

        return booking;
    }

    private Double getCalculatedBookingAmount(Car car, LocalDate rentalDate, LocalDate returnDate) {
        Integer rentalDays = Period.between(rentalDate, returnDate).getDays();
        return rentalDays * car.getAmount();
    }

    public Booking cancelBooking(Long id) {

        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking could not be canceled"));
        LocalDate cancellationDate = LocalDate.now();

        Integer daysUntilPickup = Period.between(cancellationDate, bookingToUpdate.getDateFrom()).getDays();

        bookingToUpdate.setBookingStatus(BookingStatus.CANCELLED);

        if (daysUntilPickup <= 2) {
            bookingToUpdate.setAmount(bookingToUpdate.getAmount() * 0.2);
        } else {
            bookingToUpdate.setAmount(0D);

        }
        return bookingRepository.save(bookingToUpdate);
    }

}
