package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.*;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.BranchRepository;
import com.sda10.carrental.repository.CarRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BranchControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

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

    private List<CarDto> createCarDtoList() {
        CarDto carDto = createCarDto();


        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(carDto);
        return carDtos;
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

    private List<EmployeeDto> createEmployeeDtoList() {
        EmployeeDto employeeDto = createEmployeeDto();

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(employeeDto);
        return employeeDtos;
    }

    private EmployeeDto createEmployeeDto() {
        return EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);
    }

    private List<Employee> createEmployeeList() {
        Employee employee = createEmployee();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        return employeeList;
    }

    private List<Car> createCarList() {
        Car car = createCar();

        List<Car> availableCarList = new ArrayList<>();
        availableCarList.add(car);
        return availableCarList;

    }

    private void validateAllCarsAreAvailable(Branch branch) {
        for (Car car : branch.getAvailableCarsList()) {
            if (!Status.AVAILABLE.equals(car.getStatus())) {
                throw new RuntimeException();
            }
        }
    }

    @Test
    public void givenBranchDetails_whenAPostRequestReceived_thenCreateBranch() {

        Car car = createCar();
        Employee employee = createEmployee();

        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeMapper.toDto(employee));
        List<CarDto> carDtoList = Arrays.asList(carMapper.toDto(car));

        BranchDto branchDetails = BranchDto.branchDto()
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtoList)
                .withCar(carDtoList);

        String relativePath = "/branch";

        ResponseEntity<BranchDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), branchDetails, BranchDto.class);

        Long newId = actualResponse.getBody().id;

        BranchDto expectedResponse = branchDetails.withId(newId);

        Branch expectedBranch = branchRepository.getOne(newId);

        Assertions.assertNotNull(expectedBranch);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    public void getByIdTest() {

        Branch branch = new Branch();
        Car car = createCar();
        Employee employee = createEmployee();

        List<Employee> employeeList = Arrays.asList(employee);
        List<Car> carList = Arrays.asList(car);

        branch.setAddress("Calea Victoriei");
        branch.setEmployeeList(employeeList);
        branch.setAvailableCarsList(carList);

        branch = branchRepository.save(branch);

        String relativePath = "/branch/" + branch.getId();

        ResponseEntity<BranchDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), BranchDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {

        Branch branch = new Branch();
        Car car = createCar();
        Employee employee = createEmployee();


        List<Employee> employeeList = Arrays.asList(employee);
        List<Car> carList = Arrays.asList(car);

        branch.setAddress("Calea Victoriei");
        branch.setEmployeeList(employeeList);
        branch.setAvailableCarsList(carList);

        branch = branchRepository.saveAndFlush(branch);

        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeMapper.toDto(employee));
        List<CarDto> carDtoList = Arrays.asList(carMapper.toDto(car));

        BranchDto updatedBranchDto = BranchDto.branchDto()
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtoList)
                .withCar(carDtoList);


        String relativePath = "/branch/" + branch.getId();

        this.restTemplate.put(url(relativePath), updatedBranchDto);

        Branch updatedEntity = branchRepository.findById(branch.getId()).get();

        List<EmployeeDto> employeeDtoList2 = Arrays.asList(employeeMapper.toDto(employee));
        List<CarDto> carDtoList2 = Arrays.asList(carMapper.toDto(car));

        BranchDto verifyUpdateDto = BranchDto.branchDto()
                .withAddress(updatedEntity.getAddress())
                .withEmployees(employeeDtoList2)
                .withCar(carDtoList2);

        Assertions.assertEquals(updatedBranchDto, verifyUpdateDto);

    }

    @Test
    public void deleteTest() {

        Branch existingBranch = new Branch();
        Car car = createCar();
        Employee employee = createEmployee();

        List<Employee> employeeList = Arrays.asList(employee);
        List<Car> carList = Arrays.asList(car);

        existingBranch.setAddress("Calea Victoriei");
        existingBranch.setEmployeeList(employeeList);
        existingBranch.setAvailableCarsList(carList);

        existingBranch = branchRepository.saveAndFlush(existingBranch);

        String relativePath = "/branch/" + existingBranch.getId();
        this.restTemplate.delete(relativePath, existingBranch.getId());

        Optional<Branch> updatedBranch = this.branchRepository.findById(existingBranch.getId());

        Assertions.assertFalse(updatedBranch.isPresent());
    }
}
