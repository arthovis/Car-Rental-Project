package com.sda10.carrental.controller;

import com.sda10.carrental.dto.BookingDto;
import com.sda10.carrental.dto.BookingMapper;
import com.sda10.carrental.dto.RentalDto;
import com.sda10.carrental.dto.RentalMapper;
import com.sda10.carrental.model.Booking;
import com.sda10.carrental.model.Rental;
import com.sda10.carrental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    public BookingMapper bookingMapper;

    @Autowired
    public RentalMapper rentalMapper;

    @Autowired
    public BookingService bookingService;

    @PostMapping(value = "/bookings")
    private BookingDto createBooking(@RequestBody BookingDto bookingDetails) {

        Booking booking = bookingMapper.toEntity(bookingDetails);
        booking = bookingService.createBooking(booking.getClient(), booking.getCar(), booking.getDateFrom().getRentalDate(), booking.getCarReturn().getDateOfReturn());

        return bookingMapper.toDto(booking);
    }

    @GetMapping(value = "/bookings/{id}")
    public BookingDto findById(@PathVariable Long id) {
        Booking bookingById = bookingService.findBookingById(id);

        return bookingMapper.toDto(bookingById);
    }

    @PutMapping(value = "/bookings/{id}")
    public ResponseEntity updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDetails) {
        try {
            Booking booking = bookingMapper.toEntity(bookingDetails);

            bookingService.updateBooking(id, booking);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Booking could not be updated", e);
        }
    }

    @DeleteMapping(value = "/bookings/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Booking was not deleted", e);
        }
    }

    @PostMapping(value = "/bookings/{id}/cancellations")
    private BookingDto cancelBooking(@PathVariable Long id) {

        Booking booking = bookingService.cancelBooking(id);

        return bookingMapper.toDto(booking);

    }

    @PostMapping(value = "/bookings/{id}/rentals")
    public ResponseEntity<BookingDto> addEmployeeAndCommentToRental(@PathVariable Long id, @RequestBody RentalDto rentalDto) {
        Rental rentalWithDetails = rentalMapper.toEntity(rentalDto);
        Booking updatedBooking = bookingService.updateRental(id, rentalWithDetails);
        BookingDto response = bookingMapper.toDto(updatedBooking);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
