package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarReturnDto;
import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.repository.CarReturnRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private CarReturnRepository carReturnRepository;

    @AfterEach
    public void afterEach() {
        this.carReturnRepository.deleteAll();
    }

    @Test
    public void givenCarReturnDetails_whenAPostRequestReceived_thenCreateCarReturn() {

        CarReturnDto carReturnDetails = CarReturnDto.carReturnDto()
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

        CarReturn savedCarReturn = createAndSaveCarReturn();

        CarReturnDto expectedResponse = CarReturnDto.carReturnDto()
                .withId(savedCarReturn.getId())
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

        CarReturn savedCarReturn = createAndSaveCarReturn();

        CarReturnDto newCarReturnDetails = CarReturnDto.carReturnDto()
                .withDateOfReturn(LocalDate.of(2020, 11, 11))
                .withAdditionalPayment(33.0)
                .withComments("first car updated");

        String relativePath = "/car-return/" + savedCarReturn.getId();
        this.restTemplate.put(url(relativePath), newCarReturnDetails);

        CarReturn updatedCarReturn = this.carReturnRepository.findById(savedCarReturn.getId()).get();

        CarReturnDto verifyUpdateDto = CarReturnDto.carReturnDto()
                .withDateOfReturn(updatedCarReturn.getDateOfReturn())
                .withAdditionalPayment(updatedCarReturn.getAdditionalPayment())
                .withComments(updatedCarReturn.getComments());

        Assertions.assertEquals(newCarReturnDetails, verifyUpdateDto);
    }

    @Test
    public void givenExistingCarReturn_WhenDeleteRequestIsReceived_ThenCarRetuenIsDeleted() {

        CarReturn savedCarReturn = createAndSaveCarReturn();

        String relativePath = "/car-return/" + savedCarReturn.getId();
        this.restTemplate.delete(relativePath, savedCarReturn.getId());

        Optional<CarReturn> deletedCarReturn = this.carReturnRepository.findById(savedCarReturn.getId());

        Assertions.assertFalse(deletedCarReturn.isPresent());
    }

    private CarReturn createAndSaveCarReturn() {
        CarReturn carReturn = this.createCarReturn();

        return carReturnRepository.saveAndFlush(carReturn);
    }

    private CarReturn createCarReturn() {

        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(22.0);
        carReturn.setComments("first car");

        return carReturn;

    }
}
