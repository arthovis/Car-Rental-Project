package com.sda10.carrental.controller;

import com.sda10.carrental.dto.BookingDto;
import com.sda10.carrental.model.Booking;
import com.sda10.carrental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    public BookingService bookingService;

    @PostMapping(value = "/bookings")
    private BookingDto createBooking(@RequestBody BookingDto bookingDetails) {
        Booking booking = new Booking();

        booking.setDateOfBooking(bookingDetails.dateOfBooking);
//        booking.setClient(bookingDetails.client);
//        booking.setCar(bookingDetails.car);
        booking.setDateFrom(bookingDetails.dateFrom);
        booking.setDateTo(bookingDetails.dateTo);
//        booking.setRentalBranch(bookingDetails.rentalBranch);
//        booking.setReturnBranch(bookingDetails.returnBranch);
        booking.setAmount(bookingDetails.amount);

        booking = bookingService.createBooking(booking);

        return BookingDto.bookingDto()
                .withId(booking.getId())
                .withDateOfBooking(booking.getDateOfBooking())
//                .withClient(booking.getClient())
//                .withCar(booking.getCar())
                .withDateFrom(booking.getDateFrom())
                .withDateTo(booking.getDateTo())
//                .withRentalBranch(booking.getRentalBranch())
//                .withReturnBranch(booking.getReturnBranch())
                .withAmount(booking.getAmount());

    }

    @GetMapping(value = "/bookings/{id}")
    public BookingDto findById(@PathVariable Long id) {
        Booking bookingById = bookingService.findBookingById(id);

        return BookingDto.bookingDto()
                .withDateOfBooking(bookingById.getDateOfBooking())
//                .withClient(bookingById.getClient())
//                .withCar(bookingById.getCar())
                .withDateFrom(bookingById.getDateFrom())
                .withDateTo(bookingById.getDateTo())
//                .withRentalBranch(bookingById.getRentalBranch())
//                .withReturnBranch(bookingById.getReturnBranch())
                .withAmount(bookingById.getAmount());
    }

    @PutMapping(value = "/bookings/{id}")
    public ResponseEntity updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDetails) {
        try {
            Booking booking = new Booking();

            booking.setDateOfBooking(bookingDetails.dateOfBooking);
//            booking.setClient(bookingDetails.client);
//            booking.setCar(bookingDetails.car);
            booking.setDateFrom(bookingDetails.dateFrom);
            booking.setDateTo(bookingDetails.dateTo);
//            booking.setRentalBranch(bookingDetails.rentalBranch);
//            booking.setReturnBranch(bookingDetails.returnBranch);
            booking.setAmount(bookingDetails.amount);

            bookingService.updateBooking(id, booking);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Booking could not be updated");
        }
    }

    @DeleteMapping(value = "/bookings/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Booking was not deleted");
        }
    }
}
