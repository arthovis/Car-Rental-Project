package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.RentalDto;
import com.sda10.carrental.model.Rental;
import com.sda10.carrental.repository.RentalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;

public class RentalControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void givenRentalDetails_whenAPostRequestReceived_thenCreateRental() {

        RentalDto rentalDetails = RentalDto.rentalDto();

        rentalDetails.withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");

        String relativePath = "/rental";

        ResponseEntity<RentalDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), rentalDetails, RentalDto.class);

        Long newId = actualResponse.getBody().id;

        RentalDto expectedResponse = rentalDetails.withId(newId);

        Rental expectedRental = rentalRepository.getOne(newId);

        Assertions.assertNotNull(expectedRental);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }
}
