package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.model.JobPosition;
import com.sda10.carrental.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class EmployeeControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Test
    public void givenEmployeeDetails_whenPostRequestReceived_thenCreateEmployee() {
        EmployeeDto employeeDetails = EmployeeDto.employeeDto();


        employeeDetails.withNameAndSurname("A")
                .withJobPosition(JobPosition.EMPLOYEE);

        String relativePath = "/employees";

        ResponseEntity<EmployeeDto> actualResponse = this.testRestTemplate
                .postForEntity(url(relativePath), employeeDetails, EmployeeDto.class);

        Long newId = actualResponse.getBody().id;

        EmployeeDto expectedResponse = employeeDetails.withId(newId);

        Employee expectedEmployee = employeeRepository.getOne(newId);

        Assertions.assertNotNull(expectedEmployee);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void getByIdTest() {
        Employee employee = new Employee();

        employee.setNameAndSurname("A");
        employee.setJobPosition(JobPosition.EMPLOYEE);

        employee = employeeRepository.save(employee);

        String relativePath = "/employees/" + employee.getId();

        ResponseEntity<EmployeeDto> actualResponse = this.testRestTemplate
                .getForEntity(url(relativePath), EmployeeDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {
        Employee employee = new Employee();

        employee.setNameAndSurname("A");
        employee.setJobPosition(JobPosition.EMPLOYEE);

        employee = employeeRepository.saveAndFlush(employee);

        EmployeeDto updatedEmployeeDto = EmployeeDto.employeeDto()
                .withNameAndSurname("B")
                .withJobPosition(JobPosition.MANAGER);

        String relativePath = "/employees/" + employee.getId();

        this.testRestTemplate.put(url(relativePath), updatedEmployeeDto);

        Employee updatedEntity = employeeRepository.findById(employee.getId()).get();

        EmployeeDto verifyUpdateDto = EmployeeDto.employeeDto()
                .withNameAndSurname(updatedEntity.getNameAndSurname())
                .withJobPosition(updatedEntity.getJobPosition());

        Assertions.assertEquals(updatedEmployeeDto, verifyUpdateDto);
    }

    @Test
    public void deleteTest() {
        Employee existingEmployee = new Employee();

        existingEmployee.setNameAndSurname("A");
        existingEmployee.setJobPosition(JobPosition.EMPLOYEE);
        existingEmployee = employeeRepository.save(existingEmployee);

        String relativePath = "/employees/" + existingEmployee.getId();

        this.testRestTemplate.delete(relativePath, existingEmployee.getId());

        Optional<Employee> updatedEmployee = this.employeeRepository.findById(existingEmployee.getId());

        Assertions.assertFalse(updatedEmployee.isPresent());
    }

}






















