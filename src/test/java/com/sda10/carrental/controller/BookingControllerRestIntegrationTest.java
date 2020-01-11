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
import java.time.Period;
import java.util.Optional;

public class BookingControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;
    @Autowired
    public BookingRepository bookingRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    CarReturnRepository carReturnRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    RentalMapper rentalMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    CarReturnMapper carReturnMapper;

    @Autowired
    BookingMapper bookingMapper;

    @AfterEach
    public void afterEach() {
        this.bookingRepository.deleteAll();
        this.carReturnRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.branchRepository.deleteAll();
    }

    @Test
    public void givenBookingDetails_whenPostRequestReceived_andCarIsAvailable_thenCreateBooking() throws Exception {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.of(2019, 8, 24));

        Integer rentalDays = Period.between(rental.getRentalDate(), lightCarReturn.getDateOfReturn()).getDays();
        Double amount = rentalDays * car.getAmount();

        if (car.getStatus().equals(Status.AVAILABLE)) {

            BookingDto bookingDetails = BookingDto.bookingDto()
                    .withDateOfBooking(LocalDate.now())
                    .withClient(customerMapper.toDto(customer))
                    .withCar(carMapper.toDto(car))
                    .withDateFrom(rentalMapper.toDto(rental))
                    .withAmount(amount)
                    .withCarReturnDto(carReturnMapper.toLightDto(lightCarReturn))
                    .withStatus(BookingStatus.OPEN);

            String relativePath = "/bookings";
            ResponseEntity<BookingDto> actualResponse = this.restTemplate
                    .postForEntity(url(relativePath), bookingDetails, BookingDto.class);

            Long newId = actualResponse.getBody().id;
            Optional<Booking> expectedBooking = bookingRepository.findById(newId);
            BookingDto expectedResponse = bookingDetails.withId(newId);
            expectedResponse.dateFrom.withId(actualResponse.getBody().dateFrom.id);

            Assertions.assertTrue(expectedBooking.isPresent());
            Assertions.assertEquals(expectedResponse, actualResponse.getBody());

        } else {
            throw new Exception("Car is not available, please select another car.");
        }
    }

    @Test
    public void givenBookingDetails_whenPostRequestReceived_andCarIsUnavailable_thenCreateBooking() {
        Customer customer = getSavedCustomer();
        Car car = getSavedUnavailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.of(2019, 8, 24));

        Integer rentalDays = Period.between(rental.getRentalDate(), lightCarReturn.getDateOfReturn()).getDays();
        Double amount = rentalDays * car.getAmount();

        if (!car.getStatus().equals(Status.AVAILABLE)) {

            Assertions.assertThrows(Exception.class, () -> {
                car.getStatus().equals(Status.UNAVAILABLE);
                car.getStatus().equals(Status.RENTED);
                throw new Exception("car is unavailable or rented. please select different car.");
            });
        } else {
            BookingDto bookingDetails = BookingDto.bookingDto()
                    .withDateOfBooking(LocalDate.now())
                    .withClient(customerMapper.toDto(customer))
                    .withCar(carMapper.toDto(car))
                    .withDateFrom(rentalMapper.toDto(rental))
                    .withAmount(amount)
                    .withCarReturnDto(carReturnMapper.toLightDto(lightCarReturn))
                    .withStatus(BookingStatus.OPEN);

            String relativePath = "/bookings";
            ResponseEntity<BookingDto> actualResponse = this.restTemplate
                    .postForEntity(url(relativePath), bookingDetails, BookingDto.class);

            Long newId = actualResponse.getBody().id;
            Optional<Booking> expectedBooking = bookingRepository.findById(newId);
            BookingDto expectedResponse = bookingDetails.withId(newId);
            expectedResponse.dateFrom.withId(actualResponse.getBody().dateFrom.id);

            Assertions.assertTrue(expectedBooking.isPresent());
            Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        }
    }

    private Rental buildRental(LocalDate rentalDate) {
        Rental rental = new Rental();
        rental.setRentalDate(rentalDate);

        return rental;
    }

    @Test
    public void findBookingByIdTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.now());
        Booking savedBooking = getSavedBooking(customer, car, rental, lightCarReturn);

        BookingDto expectedResponse = bookingMapper.toDto(savedBooking);

        String relativePath = "/bookings/" + savedBooking.getId();
        ResponseEntity<BookingDto> actualResponse = this.restTemplate
                .getForEntity(url(relativePath), BookingDto.class);

        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.of(2019, 3, 16));
        Booking savedBooking = getSavedBooking(customer, car, rental, lightCarReturn);

        BookingDto updatedBookingDto = BookingDto.bookingDto()
                .withDateOfBooking(LocalDate.of(2019, 12, 25))
                .withClient(customerMapper.toDto(customer))
                .withCar(carMapper.toDto(car))
                .withDateFrom(rentalMapper.toDto(rental))
                .withAmount(1000D)
                .withCarReturnDto(carReturnMapper.toLightDto(lightCarReturn))
                .withStatus(BookingStatus.OPEN);


        String relativePath = "/bookings/" + savedBooking.getId();
        this.restTemplate.put(url(relativePath), updatedBookingDto);

        Booking updatedBooking = this.bookingRepository.findById(savedBooking.getId()).get();

        BookingDto verifyUpdateDto = bookingMapper.toDto(updatedBooking);

        Assertions.assertEquals(updatedBookingDto.withId(updatedBooking.getId()), verifyUpdateDto);
    }

    @Test
    public void deleteTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.now());
        Booking savedBooking = getSavedBooking(customer, car, rental, lightCarReturn);

        String relativePath = "/bookings/" + savedBooking.getId();
        this.restTemplate.delete(url(relativePath), savedBooking.getId());

        Optional<Booking> verifyDelete = this.bookingRepository.findById(savedBooking.getId());

        Assertions.assertFalse(verifyDelete.isPresent());
    }

    @Test
    public void cancelWithFeesTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.now().minusDays(2));
        Booking bookingToCancel = getSavedBooking(customer, car, rental, lightCarReturn);

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
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.now().minusDays(3));
        Booking bookingToCancel = getSavedBooking(customer, car, rental, lightCarReturn);

        String relativePath = "/bookings/" + bookingToCancel.getId() + "/cancellations";
        this.restTemplate.postForEntity(url(relativePath), null, BookingDto.class);

        Optional<Booking> byId = bookingRepository.findById(bookingToCancel.getId());

        Assertions.assertEquals(BookingStatus.CANCELLED, byId.get().getBookingStatus());
        Assertions.assertEquals(0, byId.get().getAmount());
    }

    @Test
    public void updateRentalFromBookingTest() {
        Customer customer = getSavedCustomer();
        Car car = getSavedAvailableCar();
        CarReturn lightCarReturn = buildLightCarReturn();
        Rental rental = buildRental(LocalDate.now());
        Booking bookingToUpdate = getSavedBooking(customer, car, rental, lightCarReturn);

        Employee employee = getSavedEmployee("georgescu", JobPosition.EMPLOYEE);
        rental.setEmployee(employee);
        rental.setComments("comment");
        RentalDto rentalDto = rentalMapper.toDto(rental);

        String relativePath = "/bookings/" + bookingToUpdate.getId() + "/rentals";
        this.restTemplate.postForEntity(url(relativePath), rentalDto, RentalDto.class);
        Booking actual = bookingRepository.findById(bookingToUpdate.getId()).get();

        Assertions.assertEquals("comment", actual.getDateFrom().getComments());
        Assertions.assertEquals(employee, actual.getDateFrom().getEmployee());
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

/* // Alex
    private Car getUpdatedCar() {
        Car updatedCar = new Car();
        updatedCar.setMake("Aa");
        updatedCar.setModel("Bb");
        updatedCar.setBodyType("Cc");
        updatedCar.setYearOfProduction(1988);
        updatedCar.setColor("Dd");
        updatedCar.setMileage(2019L);
        updatedCar.setStatus(Status.UNAVAILABLE);
        updatedCar.setAmount("Ee");

        updatedCar = carRepository.saveAndFlush(updatedCar);
        return updatedCar;
    }

    private Customer getUpdatedCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Aa");
        customer.setLastName("Bb");
        customer.setEmail("Cc");
        customer.setAddress("Dd");

        customer = customerRepository.saveAndFlush(customer);
        return customer;
    }
*/

    private CarReturn buildLightCarReturn() {
        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(LocalDate.of(2019, 8, 30));
        carReturn.setAdditionalPayment(0.0);

        return carReturn;
    }

    private Booking getSavedBooking(Customer customer, Car car, Rental rental, CarReturn carReturn) {
        Booking booking = new Booking();
        booking.setDateOfBooking(LocalDate.now());
        booking.setClient(customer);
        booking.setCar(car);
        booking.setDateFrom(rental);
        booking.setAmount(600D);
        booking.setCarReturn(carReturn);
        booking.setBookingStatus(BookingStatus.OPEN);

        return this.bookingRepository.saveAndFlush(booking);
    }

    private Employee getSavedEmployee(String nameAndSurname, JobPosition jobPosition) {
        Employee employee = new Employee();
        employee.setNameAndSurname(nameAndSurname);
        employee.setJobPosition(jobPosition);

        return this.employeeRepository.saveAndFlush(employee);
    }

}
