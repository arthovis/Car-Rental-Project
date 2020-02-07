package com.sda10.carrental.controller;

import com.sda10.carrental.dto.BookingDto;
import com.sda10.carrental.dto.BookingMapper;
import com.sda10.carrental.model.Booking;
import com.sda10.carrental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookingController {

    @Autowired
    public BookingMapper bookingMapper;

    @Autowired
    public BookingService bookingService;

    @PostMapping(value = "/bookings")
    private BookingDto createBooking(@RequestBody BookingDto bookingDetails) {

        Booking booking = bookingMapper.toEntity(bookingDetails);
        booking = bookingService.createBooking(booking.getClient(), booking.getCar(), booking.getDateFrom(), booking.getDateTo(), booking.getRentalBranch(), booking.getReturnBranch());

        return bookingMapper.toDto(booking);
    }

    @GetMapping(value = "/bookings/{id}")
    public BookingDto findById(@PathVariable Long id) {
        Booking bookingById = bookingService.findBookingById(id);

        return bookingMapper.toDto(bookingById);
    }

    @GetMapping(value = "/bookings")
    public ResponseEntity<List<BookingDto>> getBookingPage(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "5") Integer pageSize) {

        List<Booking> bookings = bookingService.getAllBookings(pageIndex, pageSize);
        List<BookingDto> response = bookings
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @GetMapping(value = "/bookings/{id}/cancellations")
    private ResponseEntity<BookingDto> cancelBooking(@PathVariable Long id) {

        Booking booking = bookingService.cancelBooking(id);

        BookingDto response = bookingMapper.toDto(booking);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
