package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.BranchDto;
import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.BranchRepository;
import com.sda10.carrental.repository.CarRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import com.sda10.carrental.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
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
    private CarService carService;

    private Car createCar() {
        Car car = new Car();

        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(2015);
        car.setColor("D");
        car.setMileage(100L);
        car.setStatus(Status.AVAILABLE);
        car.setAmount("G");

        return carService.createCar(car);
    }

    private Employee createEmployee() {
        Employee employee = new Employee();

        employee.setNameAndSurname("A");
        employee.setJobPosition(JobPosition.EMPLOYEE);

        return employeeRepository.saveAndFlush(employee);
    }

    private List<CarDto> createCarDtoList() {
        CarDto carDto1 = CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus(Status.AVAILABLE)
                .withAmount("G");


        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(carDto1);
        return carDtos;
    }

    private List<EmployeeDto> createEmployeeDtoList() {
        EmployeeDto employeeDto = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(employeeDto);
        return employeeDtos;
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

        List<EmployeeDto> employeeDtoList = createEmployeeDtoList();
        List<CarDto> carDtoList = createCarDtoList();

        BranchDto branchDetails = BranchDto.branchDto();

        branchDetails.withAddress("Calea Victoriei")
//                .withEmployees(employeeDtoList)
                .withCar(carDtoList);

        String relativePath = "/branch";

        ResponseEntity<BranchDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), branchDetails, BranchDto.class);

        Long newId = actualResponse.getBody().id;

        BranchDto expectedResponse = branchDetails.withId(newId);

        Branch expectedBranch = getBranch(newId);

        Assertions.assertNotNull(expectedBranch);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Transactional
    private Branch getBranch(Long id) {
        Branch expectedBranch = branchRepository.findById(id).get();

        expectedBranch.toString();

        return expectedBranch;
    }

}
