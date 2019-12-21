package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.BranchMapper;
import com.sda10.carrental.dto.EmployeeMapper;
import com.sda10.carrental.dto.RentalDto;
import com.sda10.carrental.dto.RentalMapper;
import com.sda10.carrental.model.*;
import com.sda10.carrental.repository.BranchRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import com.sda10.carrental.repository.RentalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private BranchMapper branchMapper;

    @AfterEach
    public void afterEach() {
        this.rentalRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.branchRepository.deleteAll();
    }

    @Test
    public void givenRentalDetails_whenAPostRequestReceived_thenCreateRental() {
        Employee employee = saveEmployee();
        Branch branch = saveBranch(employee);

        RentalDto rentalDetails = RentalDto.rentalDto()
                .withBranchDto(branchMapper.toLightDto(branch))
                .withEmployeeDto(employeeMapper.toDto(employee))
                .withRentalDate(LocalDate.of(2019, 11, 11))
                .withComments("second car");

        String relativePath = "/rental";

        ResponseEntity<RentalDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), rentalDetails, RentalDto.class);

        Long newId = actualResponse.getBody().id;

        RentalDto expectedResponse = rentalDetails.withId(newId);

        Rental expectedRental = rentalRepository.getOne(newId);

        Assertions.assertNotNull(expectedRental);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    public void getByIdTest() {

        Employee employee = saveEmployee();
        Branch branch = saveBranch(employee);
        Rental savedRental = saveRental(employee, branch);

        RentalDto expectedResponse = RentalDto.rentalDto()
                .withId(savedRental.getId())
                .withBranchDto(branchMapper.toLightDto(savedRental.getBranch()))
                .withEmployeeDto(employeeMapper.toDto(savedRental.getEmployee()))
                .withRentalDate(savedRental.getRentalDate())
                .withComments(savedRental.getComments());

        String relativePath = "/rental/" + savedRental.getId();

        ResponseEntity<RentalDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), RentalDto.class);

        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {

        Employee employee = saveEmployee();
        Branch branch = saveBranch(employee);
        Rental newRental = saveRental(employee, branch);

        RentalDto newRentalDetails = RentalDto.rentalDto()
                .withBranchDto(branchMapper.toLightDto(newRental.getBranch()))
                .withEmployeeDto(employeeMapper.toDto(newRental.getEmployee()))
                .withRentalDate(LocalDate.of(2020, 11, 11))
                .withComments("my comments");

        String relativePath = "/rental/" + newRental.getId();

        this.restTemplate.put(url(relativePath), newRentalDetails);

        Rental updatedEntity = this.rentalRepository.findById(newRental.getId()).get();

        RentalDto verifyUpdateDto = rentalMapper.toDto(updatedEntity);

        Assertions.assertEquals(newRentalDetails.withId(updatedEntity.getId()), verifyUpdateDto);
    }

    @Test
    public void deleteTest() {

        Employee savedEmployee = saveEmployee();
        Branch savedBranch = saveBranch(savedEmployee);
        Rental savedRental = saveRental(savedEmployee, savedBranch);

        String relativePath = "/rental/" + savedRental.getId();
        this.restTemplate.delete(relativePath, savedRental.getId());

        Optional<Rental> deletedRental = this.rentalRepository.findById(savedRental.getId());

        Assertions.assertFalse(deletedRental.isPresent());
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

    private Rental buildRental(Employee savedEmployee, Branch savedBranch) {
        Rental rental = new Rental();
        rental.setBranch(savedBranch);
        rental.setEmployee(savedEmployee);
        rental.setRentalDate(LocalDate.of(2019, 11, 11));
        rental.setComments("first car");
        return rental;
    }

    public Rental saveRental(Employee savedEmployee, Branch savedBranch) {

        Rental newRental = buildRental(savedEmployee, savedBranch);

        return this.rentalRepository.saveAndFlush(newRental);
    }

}
