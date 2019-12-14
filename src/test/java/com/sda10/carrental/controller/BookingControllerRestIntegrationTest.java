package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.BookingDto;
import com.sda10.carrental.dto.CarMapper;
import com.sda10.carrental.dto.CustomerMapper;
import com.sda10.carrental.model.Booking;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Customer;
import com.sda10.carrental.model.Status;
import com.sda10.carrental.repository.BookingRepository;
import com.sda10.carrental.repository.CarRepository;
import com.sda10.carrental.repository.CustomerRepository;
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
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    public BookingRepository bookingRepository;

    @Test
    public void givenBookingDetails_whenPostRequestReceived_thenCreateBooking() {
        Customer customer = getCustomer();

        Car car = getCar();

        BookingDto bookingDetails = BookingDto.bookingDto();

        bookingDetails
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customerMapper.toDto(customer))
                .withCar(carMapper.toDto(car))
                .withDateFrom(LocalDate.of(2019, 8, 24))
                .withDateTo(LocalDate.of(2019, 8, 30))
//                .withRentalBranch(CarRentalOfficeDto.carRentalOfficeDto())
//                .withReturnBranch(CarRentalOfficeDto.carRentalOfficeDto())
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
        Customer customer = getCustomer();

        Car car = getCar();

        Booking booking = new Booking();

        booking.setDateOfBooking(LocalDate.now());
        booking.setClient(customer);
        booking.setCar(car);
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
        Customer customer = getCustomer();

        Car car = getCar();

        Booking booking = new Booking();

        booking.setDateOfBooking(LocalDate.now());
        booking.setClient(customer);
        booking.setCar(car);
        booking.setDateFrom(LocalDate.of(2019, 8, 24));
        booking.setDateTo(LocalDate.of(2019, 8, 30));
//        booking.setRentalBranch("C");
//        booking.setReturnBranch("D");
        booking.setAmount(100L);

        booking = bookingRepository.saveAndFlush(booking);

        BookingDto updatedBookingDto = BookingDto.bookingDto()
                .withDateOfBooking(LocalDate.of(2019, 12, 25))
                .withClient(customerMapper.toDto(customer))
                .withCar(carMapper.toDto(car))
                .withDateFrom(LocalDate.of(2019, 3, 16))
                .withDateTo(LocalDate.of(2019, 4, 27))
//                .withRentalBranch("Z")
//                .withReturnBranch("Q")
                .withAmount(1000L);

        String relativePath = "/bookings/" + booking.getId();

        this.restTemplate.put(url(relativePath), updatedBookingDto);

        Customer updatedCustomer = getCustomer();

        Car updatedCar = getUpdatedCar(car);

        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();

        BookingDto verifyUpdateDto = BookingDto.bookingDto()
                .withDateOfBooking(updatedBooking.getDateOfBooking())
                .withClient(customerMapper.toDto(updatedCustomer))
                .withCar(carMapper.toDto(updatedCar))
                .withDateFrom(updatedBooking.getDateFrom())
                .withDateTo(updatedBooking.getDateTo())
//                .withRentalBranch(updatedBooking.getRentalBranch())
//                .withReturnBranch(updatedBooking.getReturnBranch())
                .withAmount(updatedBooking.getAmount());

        Assertions.assertEquals(updatedBookingDto, verifyUpdateDto);
    }

    @Test
    public void deleteTest() {
        Customer customer = getCustomer();

        Car car = getCar();

        Booking booking = new Booking();

        booking.setDateOfBooking(LocalDate.now());
        booking.setClient(customer);
        booking.setCar(car);
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


    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("A");
        customer.setLastName("B");
        customer.setEmail("C");
        customer.setAddress("D");

        customer = customerRepository.save(customer);
        return customer;
    }

    private Car getCar() {
        Car car = new Car();
        car.setId(1L);
        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(1988);
        car.setColor("D");
        car.setMileage(2019L);
        car.setStatus(Status.AVAILABLE);
        car.setAmount("E");

        car = carRepository.save(car);
        return car;
    }

    private Car getUpdatedCar(Car car) {
        Car updatedCar = new Car();
        updatedCar.setId(1L);
        updatedCar.setMake("A");
        updatedCar.setModel("B");
        updatedCar.setBodyType("C");
        updatedCar.setYearOfProduction(1988);
        updatedCar.setColor("D");
        updatedCar.setMileage(2019L);
        updatedCar.setStatus(Status.AVAILABLE);
        updatedCar.setAmount("E");

        updatedCar = carRepository.save(car);
        return updatedCar;
    }
}
