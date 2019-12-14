package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.BookingDto;
import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.CarRentalOfficeDto;
import com.sda10.carrental.dto.CustomerDto;
import com.sda10.carrental.model.Booking;
import com.sda10.carrental.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public class BookingControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    public BookingRepository bookingRepository;

    @Test
    public void givenBookingDetails_whenPostRequestReceived_thenCreateCustomer() {


        BookingDto bookingDetails = BookingDto.bookingDto();

        bookingDetails
                .withDateOfBooking(LocalDate.now())
                .withClient(CustomerDto.customerDto())
                .withCar(CarDto.carDto())
                .withDateFrom(LocalDate.of(2019, 8, 24))
                .withDateTo(LocalDate.of(2019, 8, 30))
                .withRentalBranch(CarRentalOfficeDto.carRentalOfficeDto())
                .withReturnBranch(CarRentalOfficeDto.carRentalOfficeDto())
                .withAmount(100L);

        String relativePath = "/bookings";

        ResponseEntity<BookingDto> actualResponse = this.restTemplate
                .postForEntity(url(relativePath), bookingDetails, BookingDto.class);

        Long newId = actualResponse.getBody().id;

        BookingDto expectedResponse = bookingDetails.withId(newId);

        Booking expectedBooking = bookingRepository.getOne(newId);

        Assertions.assertNotNull(expectedBooking);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    public void findBookingByIdTest() {
        Booking booking = new Booking();

        booking.setDateOfBooking(LocalDate.now());
//        booking.setClient("A");
//        booking.setCar("B");
        booking.setDateFrom(LocalDate.of(2019, 8, 24));
        booking.setDateTo(LocalDate.of(2019, 8, 30));
//        booking.setRentalBranch("C");
//        booking.setReturnBranch("D");
        booking.setAmount(100L);

        booking = bookingRepository.save(booking);

        String relativePath = "/bookings/" + booking.getId();

        ResponseEntity<BookingDto> actualResponse = this.restTemplate
                .getForEntity(url(relativePath), BookingDto.class);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {
        Booking booking = new Booking();

        booking.setDateOfBooking(LocalDate.now());
//        booking.setClient("A");
//        booking.setCar("B");
        booking.setDateFrom(LocalDate.of(2019, 8, 24));
        booking.setDateTo(LocalDate.of(2019, 8, 30));
//        booking.setRentalBranch("C");
//        booking.setReturnBranch("D");
        booking.setAmount(100L);

        booking = bookingRepository.saveAndFlush(booking);

        BookingDto updatedBookingDto = BookingDto.bookingDto()
                .withDateOfBooking(LocalDate.of(2019, 12, 25))
//                .withClient("X")
//                .withCar("Y")
                .withDateFrom(LocalDate.of(2019, 3, 16))
                .withDateTo(LocalDate.of(2019, 4, 27))
//                .withRentalBranch("Z")
//                .withReturnBranch("Q")
                .withAmount(1000L);

        String relativePath = "/bookings/" + booking.getId();

        this.restTemplate.put(url(relativePath), updatedBookingDto);

        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();

        BookingDto verifyUpdateDto = BookingDto.bookingDto()
                .withDateOfBooking(updatedBooking.getDateOfBooking())
//                .withClient(updatedBooking.getClient())
//                .withCar(updatedBooking.getCar())
                .withDateFrom(updatedBooking.getDateFrom())
                .withDateTo(updatedBooking.getDateTo())
//                .withRentalBranch(updatedBooking.getRentalBranch())
//                .withReturnBranch(updatedBooking.getReturnBranch())
                .withAmount(updatedBooking.getAmount());

        Assertions.assertEquals(updatedBookingDto, verifyUpdateDto);
    }

    @Test
    public void deleteTest() {
        Booking booking = new Booking();

        booking.setDateOfBooking(LocalDate.now());
//        booking.setClient("A");
//        booking.setCar("B");
        booking.setDateFrom(LocalDate.of(2019, 8, 24));
        booking.setDateTo(LocalDate.of(2019, 8, 30));
//        booking.setRentalBranch("C");
//        booking.setReturnBranch("D");
        booking.setAmount(100L);

        booking = bookingRepository.save(booking);

        String relativePath = "/bookings/" + booking.getId();

        this.restTemplate.delete(url(relativePath), booking);

        Optional<Booking> verifyDelete = this.bookingRepository.findById(booking.getId());

        Assertions.assertFalse(verifyDelete.isPresent());
    }

}
