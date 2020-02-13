package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.RevenueDto;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class RevenueControllerRestIntegrationTest extends RestIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private CarRentalOfficeRepository carRentalOfficeRepository;

	@AfterEach
	public void afterEach() {
		this.bookingRepository.deleteAll();
		this.customerRepository.deleteAll();
		this.carRepository.deleteAll();
		this.branchRepository.deleteAll();
		this.carRentalOfficeRepository.deleteAll();
	}

	@Test
	public void revenueTest() {

        buildDataBase();
        List<Branch> list = this.branchRepository.findAll();
        RevenueDto expectedResponse = RevenueDto.revenueDto().withTotalRevenue(120.0);

        String relativePath = "/revenues?branchID=" + list.get(1).getId();
        ResponseEntity<RevenueDto> response = this.restTemplate.getForEntity(url(relativePath), RevenueDto.class);
        RevenueDto actualResponse = response.getBody();

        assertThat(expectedResponse, is(equalTo(actualResponse)));
    }

	private void buildDataBase() {

        List<Branch> branchList = new ArrayList<>();
        Branch branch1 = buildBranch("Calea Victoriei");
        Branch branch2 = buildBranch("Bucur Obor");
        branchList.add(branch1);
        branchList.add(branch2);
        this.carRentalOfficeRepository.saveAndFlush(buildCarRentalOffice("Piata Unirii", "ddddaaa.com", "Inchirieri", "Popescu Ion", "blabla", branchList));

        List<Branch> list = this.branchRepository.findAll();

        Customer customer1 = getSavedCustomer("A", "B", "D", "E");
        Car car1 = getSavedCar("A", "B", "C", 1988, "rosie", 2000L, Status.AVAILABLE, 5.0);
        this.bookingRepository.saveAndFlush(buildBooking(customer1, car1, branch1, branch1,
                LocalDate.of(2019, 8, 24), LocalDate.of(2019, 8, 30), 600.0, BookingStatus.COMPLETED));

        Customer customer2 = getSavedCustomer("F", "G", "H", "I");
        Car car2 = getSavedCar("F", "G", "H", 1994, "albastra", 2004L, Status.AVAILABLE, 10.0);
        this.bookingRepository.saveAndFlush(buildBooking(customer2, car2, branch2, branch2,
                LocalDate.of(2019, 8, 24), LocalDate.of(2019, 8, 30), 120.0, BookingStatus.CANCELLED));

    }

	private Customer getSavedCustomer(String firstName, String lastName, String email, String address) {
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setAddress(address);

		customer = customerRepository.saveAndFlush(customer);
		return customer;
	}

	private Car getSavedCar(String make, String model, String bodyType, int yearOfProduction, String color, Long mileage, Status status, double amount) {
        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setBodyType(bodyType);
        car.setYearOfProduction(yearOfProduction);
        car.setColor(color);
        car.setMileage(mileage);
        car.setStatus(status);
        car.setAmount(amount);

        car = carRepository.saveAndFlush(car);
        return car;
    }

    private Booking buildBooking(Customer customer, Car car, Branch rentalBranch, Branch returnBranch, LocalDate dateFrom, LocalDate dateTo, double amount, BookingStatus bookingStatus) {
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
        return booking;
    }

    private Branch buildBranch(String address) {
        Branch branch = new Branch();
        branch.setAddress(address);

        branch = this.branchRepository.saveAndFlush(branch);
        return branch;
    }

    private CarRentalOffice buildCarRentalOffice(String address, String internetDomain, String name, String owner, String logo, List<Branch> list) {
        CarRentalOffice carRentalOffice = new CarRentalOffice();
        carRentalOffice.setContactAddress(address);
        carRentalOffice.setInternetDomain(internetDomain);
        carRentalOffice.setName(name);
        carRentalOffice.setOwner(owner);
        carRentalOffice.setLogoType(logo);
        carRentalOffice.setBranches(list);

        return carRentalOffice;
    }

}
