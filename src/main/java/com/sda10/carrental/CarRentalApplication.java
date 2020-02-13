package com.sda10.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

/*
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

        @Autowired
        EmployeeRepository employeeRepository;

        @Autowired
        RentalRepository rentalRepository;

        @Autowired
        CarReturnRepository carReturnRepository;

        private void buildDataBase() {

            CarRentalOffice carRentalOffice = getSaveCarRentalOffice("Piata Unirii", "ddddaaa.com",
                    "Inchirieri", "Popescu Ion", "blabla");

            Branch branch1 = getSavedBranch("Calea Victoriei");
            Employee employee1 = getSavedEmployee("georgescu", JobPosition.EMPLOYEE);
            Customer customer1 = getSavedCustomer("A", "B", "D", "E");
            Car car1 = getSavedCar("A", "B", "C", 1988, "rosie", 2000L, Status.AVAILABLE, 5.0);
            bookingRepository.saveAndFlush(buildBooking(customer1, car1, branch1, branch1,
                    LocalDate.of(2019, 8, 24), LocalDate.of(2019, 8, 30), 600.0, BookingStatus.COMPLETED));

            Branch branch2 = getSavedBranch("Bucur Obor");
            Employee employee2 = getSavedEmployee("popescu", JobPosition.MANAGER);
            Customer customer2 = getSavedCustomer("F", "G", "H", "I");
            Car car2 = getSavedCar("F", "G", "H", 1994, "albastra", 2004L, Status.AVAILABLE, 10.0);
            bookingRepository.saveAndFlush(buildBooking(customer2, car2, branch2, branch2,
                    LocalDate.of(2019, 8, 24), LocalDate.of(2019, 8, 30), 120.0, BookingStatus.CANCELLED));

        }

        private Customer getSavedCustomer(String firstName, String lastName, String email, String address) {
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setAddress(address);

            return customerRepository.saveAndFlush(customer);
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

            return carRepository.saveAndFlush(car);
        }

        private Employee getSavedEmployee(String nameAndSurname, JobPosition jobPosition) {
            Employee employee = new Employee();
            employee.setNameAndSurname(nameAndSurname);
            employee.setJobPosition(jobPosition);

            return employeeRepository.saveAndFlush(employee);
        }

        private Branch getSavedBranch(String address) {
            Branch branch = new Branch();
            branch.setAddress(address);

            return branchRepository.saveAndFlush(branch);
        }

        private CarRentalOffice getSaveCarRentalOffice(String address, String internetDomain, String name, String owner, String logo) {
            CarRentalOffice carRentalOffice = new CarRentalOffice();
            carRentalOffice.setContactAddress(address);
            carRentalOffice.setInternetDomain(internetDomain);
            carRentalOffice.setName(name);
            carRentalOffice.setOwner(owner);
            carRentalOffice.setLogoType(logo);

            return carRentalOfficeRepository.saveAndFlush(carRentalOffice);
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

        @Bean
        CommandLineRunner filterData() {
            return args -> buildDataBase();
        }
*/

}