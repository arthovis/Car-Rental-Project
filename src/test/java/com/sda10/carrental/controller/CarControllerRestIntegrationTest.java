package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Status;
import com.sda10.carrental.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CarControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void givenCarDetails_whenPostRequestReceived_thenCreateCar() {
        CarDto carDetails = CarDto.carDto();

        carDetails.withMake("A");
        carDetails.withModel("B");
        carDetails.withBodyType("C");
        carDetails.withYearOfProduction(2015);
        carDetails.withColor("D");
        carDetails.withMileage(100L);
        carDetails.withStatus(Status.AVAILABLE);
        carDetails.withAmount("G");

        String relativePath = "/cars";

        ResponseEntity<CarDto> actualResponse = this.restTemplate
                .postForEntity(url(relativePath), carDetails, CarDto.class);

        Long newId = actualResponse.getBody().id;

        CarDto expectedResponse = carDetails.withId(newId);

        Car expectedCar = carRepository.getOne(newId);

        Assertions.assertNotNull(expectedCar);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    public void getByIdTest() {
        Car car = new Car();

        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(2015);
        car.setColor("D");
        car.setMileage(100L);
        car.setStatus(Status.AVAILABLE);
        car.setAmount("G");

        car = carRepository.save(car);

        String relativePath = "/cars/" + car.getId();

        ResponseEntity<CarDto> actualResponse = this.restTemplate
                .getForEntity(url(relativePath), CarDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void updateTest() {
        Car car = new Car();

        car.setMake("A");
        car.setModel("B");
        car.setBodyType("C");
        car.setYearOfProduction(2015);
        car.setColor("D");
        car.setMileage(100L);
        car.setStatus(Status.AVAILABLE);
        car.setAmount("G");

        car = carRepository.saveAndFlush(car);

        CarDto updatedCarDto = CarDto.carDto()
                .withMake("B")
                .withModel("C")
                .withBodyType("D")
                .withYearOfProduction(2005)
                .withColor("E")
                .withMileage(101L)
                .withStatus(Status.UNAVAILABLE)
                .withAmount("H");

        String relativePath="/cars/"+car.getId();

        this.restTemplate.put(url(relativePath), updatedCarDto);

        Car updateEntity=carRepository.findById(car.getId()).get();

        CarDto verifyUpdateDto=CarDto.carDto()
                .withId(updateEntity.getId())
                .withMake(updateEntity.getMake())
                .withModel(updateEntity.getModel())
                .withBodyType(updateEntity.getBodyType())
                .withYearOfProduction(updateEntity.getYearOfProduction())
                .withColor(updateEntity.getColor())
                .withMileage(updateEntity.getMileage())
                .withStatus(updateEntity.getStatus())
                .withAmount(updateEntity.getAmount());

        Assertions.assertEquals(updatedCarDto.withId(updateEntity.getId()), verifyUpdateDto);

    }

    @Test
    public void deleteTest(){
        Car existingCar=new Car();

        existingCar.setMake("A");
        existingCar.setModel("B");
        existingCar.setBodyType("C");
        existingCar.setYearOfProduction(2015);
        existingCar.setColor("D");
        existingCar.setMileage(100L);
        existingCar.setStatus(Status.AVAILABLE);
        existingCar.setAmount("G");

        existingCar=carRepository.save(existingCar);

        String  relativePath="/cars/"+existingCar.getId();

        this.restTemplate.delete(relativePath, existingCar.getId());

        Optional<Car> updatedCar=this.carRepository.findById(existingCar.getId());

        Assertions.assertFalse(updatedCar.isPresent());
    }



}
