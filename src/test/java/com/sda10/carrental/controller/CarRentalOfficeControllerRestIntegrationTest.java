package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarRentalOfficeDto;
import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.repository.CarRentalOfficeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CarRentalOfficeControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRentalOfficeRepository carRentalOfficeRepository;

    @Test
    public void givenCarRentalOfficeDetails_whenAPostRequestReceived_thenCreateCarRentalOffice() {

        CarRentalOfficeDto carRentalOfficeDetails = CarRentalOfficeDto.carRentalOfficeDto();

        carRentalOfficeDetails.withName("AX")
                .withInternetDomain("B")
                .withContactAddress("C")
                .withOwner("D")
                .withLogoType("E");

        String relativePath = "/car-rental-offices";

        ResponseEntity<CarRentalOfficeDto> actualResponse = this.restTemplate.postForEntity(url(relativePath), carRentalOfficeDetails, CarRentalOfficeDto.class);

        Long newId = actualResponse.getBody().id;

        CarRentalOfficeDto expectedResponse = carRentalOfficeDetails.withId(newId);

        CarRentalOffice expectedCarRentalOffice = carRentalOfficeRepository.getOne(newId);

        Assertions.assertNotNull(expectedCarRentalOffice);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void getByIdTest(){
        //Given
        CarRentalOffice carRentalOffice=new CarRentalOffice();

        carRentalOffice.setName("A");
        carRentalOffice.setContactAddress("B");
        carRentalOffice.setInternetDomain("C");
        carRentalOffice.setOwner("D");
        carRentalOffice.setLogoType("E");

        carRentalOffice = carRentalOfficeRepository.save(carRentalOffice);

        String relativePath = "/car-rental-offices/" + carRentalOffice.getId();

        ResponseEntity<CarRentalOfficeDto> actualResponse = this.restTemplate
                .getForEntity(url(relativePath), CarRentalOfficeDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }

    @Test
    public void updateTest() {

        CarRentalOffice carRentalOffice = new CarRentalOffice();

        carRentalOffice.setName("A");
        carRentalOffice.setContactAddress("B");
        carRentalOffice.setInternetDomain("C");
        carRentalOffice.setOwner("D");
        carRentalOffice.setLogoType("E");

        carRentalOffice = carRentalOfficeRepository.saveAndFlush(carRentalOffice);

        CarRentalOfficeDto updatedCarRentalOfficeDto = CarRentalOfficeDto.carRentalOfficeDto()
                .withName("A updated")
                .withContactAddress("B updated")
                .withInternetDomain("C updated")
                .withOwner("D updated")
                .withLogoType("E updated");

        String relativePath = "/car-rental-offices/" + carRentalOffice.getId();

        this.restTemplate.put(url(relativePath), updatedCarRentalOfficeDto);

        CarRentalOffice updatedEntity = carRentalOfficeRepository.findById(carRentalOffice.getId()).get();

        CarRentalOfficeDto verifyUpdateDto = CarRentalOfficeDto.carRentalOfficeDto()
                .withName(updatedEntity.getName())
                .withContactAddress(updatedEntity.getContactAddress())
                .withInternetDomain(updatedEntity.getInternetDomain())
                .withOwner(updatedEntity.getOwner())
                .withLogoType(updatedEntity.getLogoType());


        Assertions.assertEquals(updatedCarRentalOfficeDto, verifyUpdateDto);

    }

    @Test
    public void deleteTest() {

        CarRentalOffice existingCarRentalOffice = new CarRentalOffice();

        existingCarRentalOffice.setName("I");
        existingCarRentalOffice.setContactAddress("II");
        existingCarRentalOffice.setInternetDomain("III");
        existingCarRentalOffice.setOwner("IV");
        existingCarRentalOffice.setLogoType("V");
        existingCarRentalOffice = carRentalOfficeRepository.save(existingCarRentalOffice);

        String relativePath = "/car-rental-offices/" + existingCarRentalOffice.getId();
        this.restTemplate.delete(relativePath, existingCarRentalOffice.getId());

        Optional<CarRentalOffice> updatedCarRentalOffice = this.carRentalOfficeRepository.findById(existingCarRentalOffice.getId());

        Assertions.assertFalse(updatedCarRentalOffice.isPresent());

    }

}
