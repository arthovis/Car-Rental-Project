package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarReturnDto;
import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.repository.CarReturnRepository;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void givenCarReturnDetails_whenAPostRequestReceived_thenCreateCarReturn() {

        CarReturnDto carReturnDetails = CarReturnDto.carReturnDto()
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(22.0)
                .withComments("first car");

        String relativePath = "/car-return";

        ResponseEntity<CarReturnDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), carReturnDetails, CarReturnDto.class);

        Long newId = actualResponse.getBody().id;
        CarReturnDto expectedResponse = carReturnDetails.withId(newId);
        Optional<CarReturn> expectedCarReturn = carReturnRepository.findById(newId);

        Assertions.assertNotNull(expectedCarReturn.isPresent());
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void givenExistingId_whenGetCarReturnById_thenReturnCarReturn() {

        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(22.0);
        carReturn.setComments("first car");

        carReturn = carReturnRepository.saveAndFlush(carReturn);

        CarReturnDto expectedResponse = CarReturnDto.carReturnDto()
                .withId(carReturn.getId())
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());

        String relativePath = "/car-return/" + carReturn.getId();

        ResponseEntity<CarReturnDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), CarReturnDto.class);

        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }

    @Test
    public void givenNewCarReturnDetails_WhenPutRequestIsReceived_ThenUpdateCarReturn() {

        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(22.0);
        carReturn.setComments("first car");

        carReturn = carReturnRepository.saveAndFlush(carReturn);

        CarReturnDto newCarReturnDto = CarReturnDto.carReturnDto()
                .withDateOfReturn(LocalDate.of(2020, 11, 11))
                .withAdditionalPayment(33.0)
                .withComments("first car updated");

        String relativePath = "/car-return/" + carReturn.getId();

        this.restTemplate.put(url(relativePath), newCarReturnDto);

        CarReturn updatedEntity = carReturnRepository.findById(carReturn.getId()).get();

        CarReturnDto verifyUpdateDto = CarReturnDto.carReturnDto()
                .withDateOfReturn(updatedEntity.getDateOfReturn())
                .withAdditionalPayment(updatedEntity.getAdditionalPayment())
                .withComments(updatedEntity.getComments());

        Assertions.assertEquals(newCarReturnDto, verifyUpdateDto);

    }

    @Test
    public void givenExistingCarReturn_WhenDeleteRequestIsReceived_ThenCarRetuenIsDeleted() {

        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
        carReturn.setAdditionalPayment(22.0);
        carReturn.setComments("first car");

        carReturn = carReturnRepository.saveAndFlush(carReturn);

        String relativePath = "/car-return/" + carReturn.getId();
        this.restTemplate.delete(relativePath, carReturn.getId());

        Optional<CarReturn> deletedCarReturn = this.carReturnRepository.findById(carReturn.getId());

        Assertions.assertFalse(deletedCarReturn.isPresent());

    }
}
