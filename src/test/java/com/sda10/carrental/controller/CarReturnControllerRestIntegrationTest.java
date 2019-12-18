package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.BranchMapper;
import com.sda10.carrental.dto.CarReturnDto;
import com.sda10.carrental.dto.CarReturnMapper;
import com.sda10.carrental.dto.EmployeeMapper;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.BranchRepository;
import com.sda10.carrental.repository.CarReturnRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class CarReturnControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CarReturnRepository carReturnRepository;

    @Autowired
    private CarReturnMapper carReturnMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private BranchMapper branchMapper;

    @AfterEach
    public void afterEach() {
        this.carReturnRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.branchRepository.deleteAll();
    }

    @Test
    public void givenCarReturnDetails_whenAPostRequestReceived_thenCreateCarReturn() {
        Employee employee = saveEmployee();
        Branch branch = saveBranch(employee);

        CarReturnDto carReturnDetails = CarReturnDto.carReturnDto()
                .withBranchDto(branchMapper.toLightDto(branch))
                .withEmployeeDto(employeeMapper.toDto(employee))
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(22.0)
                .withComments("second car");

        String relativePath = "/car-return";
        ResponseEntity<CarReturnDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), carReturnDetails, CarReturnDto.class);

        Long newId = actualResponse.getBody().id;
        Optional<CarReturn> expectedCarReturn = this.carReturnRepository.findById(newId);
        CarReturnDto expectedResponse = carReturnDetails.withId(newId);

        Assertions.assertTrue(expectedCarReturn.isPresent());
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    public void givenExistingId_whenGetCarReturnById_thenReturnCarReturn() {

        Employee employee = saveEmployee();
        Branch branch = saveBranch(employee);
        CarReturn savedCarReturn = saveCarReturn(employee, branch);

        CarReturnDto expectedResponse = carReturnMapper.toDto(savedCarReturn);

        String relativePath = "/car-return/" + savedCarReturn.getId();
        ResponseEntity<CarReturnDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), CarReturnDto.class);

        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void givenNewCarReturnDetails_WhenPutRequestIsReceived_ThenUpdateCarReturn() {

        Employee employee = saveEmployee();
        Branch branch = saveBranch(employee);
        CarReturn newCarReturn = saveCarReturn(employee, branch);

        CarReturnDto newCarReturnDetails = CarReturnDto.carReturnDto()
                .withBranchDto(branchMapper.toLightDto(newCarReturn.getBranch()))
                .withEmployeeDto(employeeMapper.toDto(newCarReturn.getEmployee()))
                .withDateOfReturn(LocalDate.of(2020, 11, 11))
                .withAdditionalPayment(33.0)
                .withComments("my comments");

        String relativePath = "/car-return/" + newCarReturn.getId();
        this.restTemplate.put(url(relativePath), newCarReturnDetails);

        CarReturn updatedCarReturn = this.carReturnRepository.findById(newCarReturn.getId()).get();

        CarReturnDto verifyUpdateDto = carReturnMapper.toDto(updatedCarReturn);

        Assertions.assertEquals(newCarReturnDetails.withId(updatedCarReturn.getId()), verifyUpdateDto);
    }

    @Test
    public void givenExistingCarReturn_WhenDeleteRequestIsReceived_ThenCarReturnIsDeleted() {

        Employee savedEmployee = saveEmployee();
        Branch branch = saveBranch(savedEmployee);
        CarReturn savedCarReturn = saveCarReturn(savedEmployee, branch);

        String relativePath = "/car-return/" + savedCarReturn.getId();
        this.restTemplate.delete(relativePath, savedCarReturn.getId());

        Optional<CarReturn> deletedCarReturn = this.carReturnRepository.findById(savedCarReturn.getId());

        Assertions.assertFalse(deletedCarReturn.isPresent());
    }

    private Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setNameAndSurname("popescu ion");
        employee.setJobPosition(JobPosition.MANAGER);
        return employee;
    }

    public Employee saveEmployee() {
        return this.employeeRepository.saveAndFlush(buildEmployee());
    }

    private Branch buildBranch(Employee savedEmployee) {
        List<Car> carList = new ArrayList<>();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(savedEmployee);

        Branch branch = new Branch();
        branch.setAvailableCarsList(carList);
        branch.setEmployeeList(employeeList);
        branch.setAddress("Calea Victoriei");
        return branch;
    }

    public Branch saveBranch(Employee savedEmployee) {

        Branch newBranch = buildBranch(savedEmployee);

        return this.branchRepository.saveAndFlush(newBranch);
    }

    private CarReturn buildCarReturn(Employee savedEmployee, Branch savedBranch) {
        CarReturn carReturn = new CarReturn();
        carReturn.setBranch(savedBranch);
        carReturn.setEmployee(savedEmployee);
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(0.0);
        carReturn.setComments("first car");
        return carReturn;
    }

    public CarReturn saveCarReturn(Employee savedEmployee, Branch savedBranch) {

        CarReturn newCarReturn = buildCarReturn(savedEmployee, savedBranch);

        return this.carReturnRepository.saveAndFlush(newCarReturn);
    }
}
