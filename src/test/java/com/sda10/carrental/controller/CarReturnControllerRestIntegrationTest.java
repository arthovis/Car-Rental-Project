package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarReturnDto;
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

    private Employee newEmployee = buildEmployee();

    private Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setNameAndSurname("popescu ion");
        employee.setJobPosition("manager");
        return employee;
    }

    private CarReturn newCarReturn = buildCarReturn();

    private CarReturn buildCarReturn() {
        CarReturn carReturn = new CarReturn();
        carReturn.setEmployee(this.newEmployee);
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(22.0);
        carReturn.setComments("first car");
        return carReturn;
    }

    private Employee createdEmployee;
    private CarReturn createdCarReturn;

    @BeforeEach
    public void beforeEach() {
        createdEmployee = this.employeeRepository.saveAndFlush(newEmployee);
        createdCarReturn = this.carReturnRepository.saveAndFlush(newCarReturn);
    }

    @AfterEach
    public void afterEach() {
        this.carReturnRepository.deleteAll();
        this.employeeRepository.deleteAll();
    }

/*
    @Test
    public void testEnt(){
        Assertions.assertEquals("first car", newCarReturn.getComments());
        Assertions.assertEquals("manager", createdEmployee.getJobPosition());
    }
*/

    @Test
    public void givenCarReturnDetails_whenAPostRequestReceived_thenCreateCarReturn() {

        CarReturnDto carReturnDetails = CarReturnDto.carReturnDto()
                .withEmployee(this.createdEmployee)
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

        CarReturn savedCarReturn = this.createdCarReturn;

        CarReturnDto expectedResponse = CarReturnDto.carReturnDto()
                .withId(savedCarReturn.getId())
                .withEmployee(savedCarReturn.getEmployee())
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

        CarReturn savedCarReturn = this.createdCarReturn;

        CarReturnDto newCarReturnDetails = CarReturnDto.carReturnDto()
                .withEmployee(this.createdEmployee)
                .withDateOfReturn(LocalDate.of(2020, 11, 11))
                .withAdditionalPayment(33.0)
                .withComments("first car updated");

        String relativePath = "/car-return/" + savedCarReturn.getId();
        this.restTemplate.put(url(relativePath), newCarReturnDetails);

        CarReturn updatedCarReturn = this.carReturnRepository.findById(savedCarReturn.getId()).get();

        CarReturnDto verifyUpdateDto = CarReturnDto.carReturnDto()
                .withEmployee(updatedCarReturn.getEmployee())
                .withDateOfReturn(updatedCarReturn.getDateOfReturn())
                .withAdditionalPayment(updatedCarReturn.getAdditionalPayment())
                .withComments(updatedCarReturn.getComments());

        Assertions.assertEquals(newCarReturnDetails, verifyUpdateDto);
    }

    @Test
    public void givenExistingCarReturn_WhenDeleteRequestIsReceived_ThenCarRetuenIsDeleted() {

        CarReturn savedCarReturn = this.createdCarReturn;

        String relativePath = "/car-return/" + savedCarReturn.getId();
        this.restTemplate.delete(relativePath, savedCarReturn.getId());

        Optional<CarReturn> deletedCarReturn = this.carReturnRepository.findById(savedCarReturn.getId());

        Assertions.assertFalse(deletedCarReturn.isPresent());
    }
}
