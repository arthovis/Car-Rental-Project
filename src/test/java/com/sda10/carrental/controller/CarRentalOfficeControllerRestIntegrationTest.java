package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.*;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.BranchRepository;
import com.sda10.carrental.repository.CarRentalOfficeRepository;
import com.sda10.carrental.repository.CarRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CarRentalOfficeControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRentalOfficeRepository carRentalOfficeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private BranchMapper branchMapper;

    public Car createCar() {
        Car car = new Car();

        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(2015);
        car.setColor("D");
        car.setMileage(100L);
        car.setStatus(Status.AVAILABLE);
        car.setAmount(5D);

        return carRepository.save(car);
    }

    public Employee createEmployee() {
        Employee employee = new Employee();

        employee.setNameAndSurname("A");
        employee.setJobPosition(JobPosition.EMPLOYEE);

        return employeeRepository.save(employee);
    }

    private EmployeeDto createEmployeeDto() {
        return EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);
    }

    private CarDto createCarDto() {
        return CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus(Status.AVAILABLE)
                .withAmount(5D);
    }

    public Branch createBranch() {

        Branch branch = new Branch();
        Car car = createCar();
        Employee employee = createEmployee();

        List<Employee> employeeList = Arrays.asList(employee);
        List<Car> carList = Arrays.asList(car);

        branch.setAddress("Calea Victoriei");
        branch.setEmployeeList(employeeList);
        branch.setAvailableCarsList(carList);

        return branchRepository.save(branch);
    }

    public BranchDto createBranchDto() {

        Car car = createCar();
        Employee employee = createEmployee();

        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeMapper.toDto(employee));
        List<CarDto> carDtoList = Arrays.asList(carMapper.toDto(car));

        return BranchDto.branchDto()
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtoList)
                .withCar(carDtoList);
    }


    @Test
    public void givenCarRentalOfficeDetails_whenAPostRequestReceived_thenCreateCarRentalOffice() {

        Branch branch = createBranch();

        List<BranchDto> branchDtoList = Arrays.asList(branchMapper.toDto(branch));

        CarRentalOfficeDto carRentalOfficeDetails = CarRentalOfficeDto.carRentalOfficeDto();

        carRentalOfficeDetails.withName("A")
                .withInternetDomain("B")
                .withContactAddress("C")
                .withOwner("D")
                .withLogoType("E")
                .withBranches(branchDtoList);

        String relativePath = "/car-rental-offices";

        ResponseEntity<CarRentalOfficeDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), carRentalOfficeDetails, CarRentalOfficeDto.class);

        Long newId = actualResponse.getBody().id;

        CarRentalOfficeDto expectedResponse = carRentalOfficeDetails.withId(newId);

        CarRentalOffice expectedCarRentalOffice = carRentalOfficeRepository.getOne(newId);

        Assertions.assertNotNull(expectedCarRentalOffice);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void getByIdTest() {
        CarRentalOffice carRentalOffice = new CarRentalOffice();
        Branch branch = createBranch();

        List<Branch> branchList = Arrays.asList(branch);

        carRentalOffice.setName("A");
        carRentalOffice.setContactAddress("B");
        carRentalOffice.setInternetDomain("C");
        carRentalOffice.setOwner("D");
        carRentalOffice.setLogoType("E");
        carRentalOffice.setBranches(branchList);

        carRentalOffice = carRentalOfficeRepository.save(carRentalOffice);

        String relativePath = "/car-rental-offices/" + carRentalOffice.getId();

        ResponseEntity<CarRentalOfficeDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), CarRentalOfficeDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {

        Branch branch = createBranch();

        CarRentalOffice carRentalOffice = new CarRentalOffice();

        List<Branch> branchList = Arrays.asList(branch);

        carRentalOffice.setName("A");
        carRentalOffice.setContactAddress("B");
        carRentalOffice.setInternetDomain("C");
        carRentalOffice.setOwner("D");
        carRentalOffice.setLogoType("E");
        carRentalOffice.setBranches(branchList);

        carRentalOffice = carRentalOfficeRepository.saveAndFlush(carRentalOffice);

        List<BranchDto> branchDtos = Arrays.asList(branchMapper.toDto(branch));

        CarRentalOfficeDto updatedCarRentalOfficeDto = CarRentalOfficeDto.carRentalOfficeDto()
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("C")
                .withOwner("D")
                .withLogoType("E")
                .withBranches(branchDtos);

        String relativePath = "/car-rental-offices/" + carRentalOffice.getId();

        this.restTemplate.put(url(relativePath), updatedCarRentalOfficeDto);

        CarRentalOffice updatedEntity = carRentalOfficeRepository.findById(carRentalOffice.getId()).get();

        List<BranchDto> branchDtoList = Arrays.asList(branchMapper.toDto(branch));

        CarRentalOfficeDto verifyUpdateDto = CarRentalOfficeDto.carRentalOfficeDto()
                .withName(updatedEntity.getName())
                .withInternetDomain(updatedEntity.getInternetDomain())
                .withContactAddress(updatedEntity.getContactAddress())
                .withOwner(updatedEntity.getOwner())
                .withLogoType(updatedEntity.getLogoType())
                .withBranches(branchDtoList);

        Assertions.assertEquals(updatedCarRentalOfficeDto, verifyUpdateDto);
    }

    @Test
    public void deleteTest() {
        CarRentalOffice existingCarRentalOffice = new CarRentalOffice();
        Branch branch = createBranch();

        List<Branch> branchList = Arrays.asList(branch);

        existingCarRentalOffice.setName("A");
        existingCarRentalOffice.setContactAddress("B");
        existingCarRentalOffice.setInternetDomain("C");
        existingCarRentalOffice.setOwner("D");
        existingCarRentalOffice.setLogoType("E");
        existingCarRentalOffice.setBranches(branchList);

        existingCarRentalOffice = carRentalOfficeRepository.save(existingCarRentalOffice);

        String relativePath = "/car-rental-offices/" + existingCarRentalOffice.getId();
        this.restTemplate.delete(relativePath, existingCarRentalOffice);

        Optional<CarRentalOffice> updatedCarRentaloffice = this.carRentalOfficeRepository.findById(existingCarRentalOffice.getId());

        Assertions.assertFalse(updatedCarRentaloffice.isPresent());

    }

}
