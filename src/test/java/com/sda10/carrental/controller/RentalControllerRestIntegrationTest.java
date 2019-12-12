package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.RentalDto;
import com.sda10.carrental.model.Rental;
import com.sda10.carrental.repository.RentalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public class RentalControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void givenRentalDetails_whenAPostRequestReceived_thenCreateRental() {

        RentalDto rentalDetails = RentalDto.rentalDto();

        rentalDetails.withRentalDate(LocalDate.of(1995, 02, 12))
                .withComments("Achitat");

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

        Rental rental = new Rental();

        rental.setRentalDate(LocalDate.of(2019, 5, 5));
        rental.setComments("Done");

        rental = rentalRepository.save(rental);

        String relativePath = "/rental/" + rental.getId();

        ResponseEntity<RentalDto> actualResponse = this.restTemplate.getForEntity(url(relativePath), RentalDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {

        Rental rental = new Rental();

        rental.setRentalDate(LocalDate.of(2019, 5, 5));
        rental.setComments("Done");

        rental = rentalRepository.saveAndFlush(rental);

        RentalDto updatedRentalDto = RentalDto.rentalDto()
                .withRentalDate(LocalDate.of(2019, 6, 12))
                .withComments("Achitat");

        String relativePath = "/rental/" + rental.getId();

        this.restTemplate.put(url(relativePath), updatedRentalDto);

        Rental updatedEntity = rentalRepository.findById(rental.getId()).get();

        RentalDto verifyUpdateDto = RentalDto.rentalDto()
                .withRentalDate(updatedEntity.getRentalDate())
                .withComments(updatedEntity.getComments());

        Assertions.assertEquals(updatedRentalDto, verifyUpdateDto);
    }

    @Test
    public void deleteTest() {

        Rental existingRental = new Rental();

        existingRental.setRentalDate(LocalDate.of(2012, 12, 21));
        existingRental.setComments("Spalat, curatat, gata de inchiriat!");
        existingRental = rentalRepository.save(existingRental);

        String relativePath = "/rental/" + existingRental.getId();
        this.restTemplate.delete(relativePath, existingRental.getId());

        Optional<Rental> updatedRental = this.rentalRepository.findById(existingRental.getId());

        Assertions.assertFalse(updatedRental.isPresent());
    }

}
