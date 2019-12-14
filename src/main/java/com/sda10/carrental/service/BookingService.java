package com.sda10.carrental.service;

import com.sda10.carrental.model.Booking;
import com.sda10.carrental.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    public BookingRepository bookingRepository;

    @Transactional
    public Booking createBooking(Booking booking) {
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

}
