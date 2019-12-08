package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarRentalOfficeDto;
import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.repository.CarRentalOfficeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class CarRentalOfficeControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRentalOfficeRepository carRentalOfficeRepository;

    @Test
    public void whenAPostRequestReceived_thenCreateCarRentalOffice() {

        String relativePath = "/car-rental-offices";

        ResponseEntity<CarRentalOfficeDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), null, CarRentalOfficeDto.class);

        Long newId = actualResponse.getBody().id;

        CarRentalOfficeDto expectedResponse = CarRentalOfficeDto.carRentalOfficeDto()
                .withId(newId)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("C")
                .withOwner("D")
                .withLogoType("E");

        CarRentalOffice expectedCarRentalOffice = carRentalOfficeRepository.getOne(newId);

        Assertions.assertNotNull(expectedCarRentalOffice);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

}
