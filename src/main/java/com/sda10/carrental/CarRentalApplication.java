package com.sda10.carrental;

import com.sda10.carrental.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CarRepository carRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	CarRentalOfficeRepository carRentalOfficeRepository;

/*
	private Customer getSavedCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("A");
		customer.setLastName("B");
		customer.setEmail("C");
		customer.setAddress("D");

		customer = customerRepository.saveAndFlush(customer);
		return customer;
	}

	private Car getSavedCar() {
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

	private CarReturn buildLightCarReturn() {
		CarReturn carReturn = new CarReturn();
		carReturn.setDateOfReturn(LocalDate.of(2019, 8, 30));

		return carReturn;
	}

	private Rental buildRental(LocalDate rentalDate, Branch branch) {
		Rental rental = new Rental();
		rental.setRentalDate(rentalDate);
		rental.setBranch(branch);

		return rental;
	}

	private Booking buildBooking(Customer customer, Car car, Rental rental, CarReturn carReturn) {
		Booking booking = new Booking();
		booking.setDateOfBooking(LocalDate.now());
		booking.setClient(customer);
		booking.setCar(car);
		booking.setDateFrom(rental);
		booking.setAmount(600D);
		booking.setCarReturn(carReturn);
		booking.setBookingStatus(BookingStatus.COMPLETED);

		return booking;
	}

	private Booking buildBooking1(Customer customer, Car car, Rental rental, CarReturn carReturn) {
		Booking booking1 = new Booking();
		booking1.setDateOfBooking(LocalDate.now());
		booking1.setClient(customer);
		booking1.setCar(car);
		booking1.setDateFrom(rental);
		booking1.setAmount(120D);
		booking1.setCarReturn(carReturn);
		booking1.setBookingStatus(BookingStatus.CANCELLED);

		return booking1;
	}

	private List<Branch> buildBranch() {
		Branch branch1 = new Branch();
		branch1.setAddress("Calea Victoriei");
		Branch branch2 = new Branch();
		branch2.setAddress("Bucur Obor");
		List<Branch> list = new ArrayList<>();
		list.add(branch1);
		list.add(branch2);

		list = this.branchRepository.saveAll(list);
		return list;
	}

	private CarRentalOffice buildCarRentalOffice(List<Branch> list) {
		CarRentalOffice carRentalOffice = new CarRentalOffice();
		carRentalOffice.setContactAddress("Piata Unirii");
		carRentalOffice.setInternetDomain("aaassdd@aaa.com");
		carRentalOffice.setName("Inchirieri");
		carRentalOffice.setOwner("Popescu Ion");
		carRentalOffice.setLogoType("blabla");
		carRentalOffice.setBranches(list);

		return carRentalOffice;
	}

	@Bean
	CommandLineRunner loadData() {
		return args -> {
			List<Branch> list = buildBranch();
			this.carRentalOfficeRepository.save(buildCarRentalOffice(list));

			Customer customer = getSavedCustomer();
			Car car = getSavedCar();
			CarReturn lightCarReturn = buildLightCarReturn();
			Rental rental = buildRental(LocalDate.of(2019, 8, 24), list.get(0));
			this.bookingRepository.save(buildBooking(customer, car, rental, lightCarReturn));

			Customer customer1 = getSavedCustomer();
			Car car1 = getSavedCar();
			CarReturn lightCarReturn1 = buildLightCarReturn();
			Rental rental1 = buildRental(LocalDate.of(2019, 8, 24), list.get(1));
			this.bookingRepository.save(buildBooking1(customer1, car1, rental1, lightCarReturn1));

		};
	}
*/

	@Autowired
	RevenueRepository revenueRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Bean
	CommandLineRunner filterData() {
		return args -> {
			System.out.println(employeeRepository.findAll());
			System.out.println(carRepository.findAll());
			System.out.println(branchRepository.findAll());

//			List<Booking> l = revenueRepository.bookingsByBranchIdAndStatus(1L);
//			System.out.println(l);
//			double result = l.stream().map(booking -> booking.getAmount()).count();
//			System.out.println(result);
		};
	}

}

