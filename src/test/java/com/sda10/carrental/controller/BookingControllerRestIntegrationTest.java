package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.*;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.*;
import org.junit.jupiter.api.AfterEach;
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
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    CarReturnRepository carReturnRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    BranchMapper branchMapper;

    @AfterEach
    public void afterEach() {
        this.bookingRepository.deleteAll();
        this.carReturnRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.branchRepository.deleteAll();
    }

    @Test
    public void givenBookingDetails_whenPostRequestReceived_andCarIsAvailable_thenCreateBooking() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        Branch rentalBranch = getSavedBranch("Calea Victoriei");
        Branch returnBranch = getSavedBranch("Bucur Obor");

        BookingDto bookingDetails = BookingDto.bookingDto()
                .withDateFrom(LocalDate.of(2019, 8, 24))
                .withDateTo(LocalDate.of(2019, 8, 30))
                .withClientDto(customerMapper.toDto(customer))
                .withCarDto(carMapper.toDto(car))
                .withRentalBranchDto(branchMapper.toDto(rentalBranch))
                .withReturnBranchDto(branchMapper.toDto(returnBranch))
                .withStatus(BookingStatus.OPEN);

        String relativePath = "/bookings";
        ResponseEntity<BookingDto> actualResponse = this.restTemplate
                .postForEntity(url(relativePath), bookingDetails, BookingDto.class);

        Long newId = actualResponse.getBody().id;
        Optional<Booking> expectedBooking = bookingRepository.findById(newId);
        BookingDto expectedResponse = bookingDetails.withId(newId)
                .withDateOfBooking(actualResponse.getBody().dateOfBooking)
                .withRentalDto(actualResponse.getBody().rentalDto)
                .withCarReturnDto(actualResponse.getBody().carReturnDto)
                .withAmount(actualResponse.getBody().amount);

        Assertions.assertTrue(expectedBooking.isPresent());
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void findBookingByIdTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        Branch rentalBranch = getSavedBranch("Calea Victoriei");
        Branch returnBranch = getSavedBranch("Bucur Obor");
        Booking savedBooking = getSavedBooking(customer, car, rentalBranch, returnBranch,
                LocalDate.of(2019, 8, 24), LocalDate.of(2019, 8, 30), 600.0, BookingStatus.COMPLETED);

        BookingDto expectedResponse = bookingMapper.toDto(savedBooking);

        String relativePath = "/bookings/" + savedBooking.getId();
        ResponseEntity<BookingDto> actualResponse = this.restTemplate
                .getForEntity(url(relativePath), BookingDto.class);

        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void deleteTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        Branch rentalBranch = getSavedBranch("Calea Victoriei");
        Branch returnBranch = getSavedBranch("Bucur Obor");
        Booking savedBooking = getSavedBooking(customer, car, rentalBranch, returnBranch,
                LocalDate.of(2019, 8, 27), LocalDate.of(20209, 1, 30), 600.0, BookingStatus.COMPLETED);

        String relativePath = "/bookings/" + savedBooking.getId();
        this.restTemplate.delete(url(relativePath), savedBooking.getId());

        Optional<Booking> verifyDelete = this.bookingRepository.findById(savedBooking.getId());

        Assertions.assertFalse(verifyDelete.isPresent());
    }

    @Test
    public void cancelWithFeesTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        Branch rentalBranch = getSavedBranch("Calea Victoriei");
        Branch returnBranch = getSavedBranch("Bucur Obor");
        Booking bookingToCancel = getSavedBooking(customer, car, rentalBranch, returnBranch,
                LocalDate.of(2020, 1, 27), LocalDate.of(2019, 8, 30), 600.0, BookingStatus.COMPLETED);

        String relativePath = "/bookings/" + bookingToCancel.getId() + "/cancellations";
        this.restTemplate.postForEntity(url(relativePath), null, BookingDto.class);

        Optional<Booking> byId = bookingRepository.findById(bookingToCancel.getId());

        Assertions.assertEquals(BookingStatus.CANCELLED, byId.get().getBookingStatus());
        Assertions.assertEquals(120, byId.get().getAmount());

    }

    @Test
    public void cancelWithoutFeesTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        Branch rentalBranch = getSavedBranch("Calea Victoriei");
        Branch returnBranch = getSavedBranch("Bucur Obor");
        Booking bookingToCancel = getSavedBooking(customer, car, rentalBranch, returnBranch,
                LocalDate.of(2020, 1, 28), LocalDate.of(2019, 8, 30), 600.0, BookingStatus.COMPLETED);

        String relativePath = "/bookings/" + bookingToCancel.getId() + "/cancellations";
        this.restTemplate.postForEntity(url(relativePath), null, BookingDto.class);

        Optional<Booking> byId = bookingRepository.findById(bookingToCancel.getId());

        Assertions.assertEquals(BookingStatus.CANCELLED, byId.get().getBookingStatus());
        Assertions.assertEquals(0, byId.get().getAmount());
    }

    private Customer getSavedCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("A");
        customer.setLastName("B");
        customer.setEmail("C");
        customer.setAddress("D");

        customer = customerRepository.saveAndFlush(customer);
        return customer;
    }

    private Car getSavedAvailableCar() {
        Car car = new Car();
        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(1988);
        car.setColor("D");
        car.setMileage(2019L);
        car.setStatus(Status.AVAILABLE);
        car.setAmount(5D);

        car = carRepository.saveAndFlush(car);
        return car;
    }

    private Car getSavedUnavailableCar() {
        Car car = new Car();
        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(1988);
        car.setColor("D");
        car.setMileage(2019L);
        car.setStatus(Status.UNAVAILABLE);
        car.setAmount(5D);

        car = carRepository.saveAndFlush(car);
        return car;
    }

    private Booking getSavedBooking(Customer customer, Car car, Branch rentalBranch, Branch returnBranch, LocalDate dateFrom, LocalDate dateTo, double amount, BookingStatus bookingStatus) {
        Booking booking = new Booking();
        booking.setDateOfBooking(LocalDate.now());
        booking.setDateFrom(dateFrom);
        booking.setDateTo(dateTo);
        booking.setClient(customer);
        booking.setCar(car);
        booking.setRentalBranch(rentalBranch);
        booking.setReturnBranch(returnBranch);
        booking.addRental(dateFrom, rentalBranch);
        booking.addReturn(dateTo, returnBranch);
        booking.setAmount(amount);
        booking.setBookingStatus(bookingStatus);

        return bookingRepository.saveAndFlush(booking);
    }

    private Branch getSavedBranch(String address) {
        Branch branch = new Branch();
        branch.setAddress(address);

        return branchRepository.saveAndFlush(branch);
    }

}
