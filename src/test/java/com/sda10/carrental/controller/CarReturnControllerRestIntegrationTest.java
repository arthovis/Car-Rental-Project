package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarReturnDto;
import com.sda10.carrental.dto.CarReturnMapper;
import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.repository.CarReturnRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public class CarReturnControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CarReturnRepository carReturnRepository;

    @Autowired
    private CarReturnMapper carReturnMapper;

    @AfterEach
    public void afterEach() {
        this.carReturnRepository.deleteAll();
        this.employeeRepository.deleteAll();
    }

    @Test
    public void givenCarReturnDetails_whenAPostRequestReceived_thenCreateCarReturn() {

        Employee employee = saveEmployee();

        CarReturnDto carReturnDetails = CarReturnDto.carReturnDto()
                .withEmployeeDto(carReturnMapper.fakeEmployeeDtoMapper(employee))
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(22.0)
                .withComments("second car");

        String relativePath = "/car-return";
        ResponseEntity<CarReturnDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), carReturnDetails, CarReturnDto.class);

        Long newId = actualResponse.getBody().id;
        Optional<CarReturn> expectedCarReturn = this.carReturnRepository.findById(newId);
        CarReturnDto expectedResponse = carReturnDetails.withId(newId);
        System.out.println(expectedResponse);
        Assertions.assertTrue(expectedCarReturn.isPresent());
        System.out.println(actualResponse.getBody());
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    public void givenExistingId_whenGetCarReturnById_thenReturnCarReturn() {

        Employee employee = saveEmployee();
        CarReturn savedCarReturn = saveCarReturn(employee);

        CarReturnDto expectedResponse = CarReturnDto.carReturnDto()
                .withId(savedCarReturn.getId())
                .withEmployeeDto(carReturnMapper.fakeEmployeeDtoMapper(savedCarReturn.getEmployee()))
                .withDateOfReturn(savedCarReturn.getDateOfReturn())
                .withAdditionalPayment(savedCarReturn.getAdditionalPayment())
                .withComments(savedCarReturn.getComments());

        String relativePath = "/car-return/" + savedCarReturn.getId();
        ResponseEntity<CarReturnDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), CarReturnDto.class);

        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void givenNewCarReturnDetails_WhenPutRequestIsReceived_ThenUpdateCarReturn() {

        Employee employee = saveEmployee();
        CarReturn newCarReturn = saveCarReturn(employee);

        CarReturnDto newCarReturnDetails = CarReturnDto.carReturnDto()
                .withEmployeeDto(carReturnMapper.fakeEmployeeDtoMapper(newCarReturn.getEmployee()))
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
    public void givenExistingCarReturn_WhenDeleteRequestIsReceived_ThenCarRetuenIsDeleted() {

        Employee savedEmployee = saveEmployee();
        CarReturn savedCarReturn = saveCarReturn(savedEmployee);

        String relativePath = "/car-return/" + savedCarReturn.getId();
        this.restTemplate.delete(relativePath, savedCarReturn.getId());

        Optional<CarReturn> deletedCarReturn = this.carReturnRepository.findById(savedCarReturn.getId());

        Assertions.assertFalse(deletedCarReturn.isPresent());
    }

    private Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setNameAndSurname("popescu ion");
        employee.setJobPosition("manager");
        return employee;
    }

    public Employee saveEmployee() {
        return this.employeeRepository.saveAndFlush(buildEmployee());
    }

    private CarReturn buildCarReturn(Employee savedEmployee) {
        CarReturn carReturn = new CarReturn();
        carReturn.setEmployee(savedEmployee);
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(22.0);
        carReturn.setComments("first car");
        return carReturn;
    }

    public CarReturn saveCarReturn(Employee savedEmployee) {

        CarReturn newCarReturn = buildCarReturn(savedEmployee);

        return this.carReturnRepository.saveAndFlush(newCarReturn);
    }
}
