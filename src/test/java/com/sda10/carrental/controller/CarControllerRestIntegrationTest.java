package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.CarMapper;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Status;
import com.sda10.carrental.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

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
        carDetails.withAmount(5D);

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
        car.setAmount(5D);

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
        car.setAmount(5D);

        car = carRepository.saveAndFlush(car);

        CarDto updatedCarDto = CarDto.carDto()
                .withMake("B")
                .withModel("C")
                .withBodyType("D")
                .withYearOfProduction(2005)
                .withColor("E")
                .withMileage(101L)
                .withStatus(Status.UNAVAILABLE)
                .withAmount(7.5);

        String relativePath = "/cars/" + car.getId();

        this.restTemplate.put(url(relativePath), updatedCarDto);

        Car updateEntity = carRepository.findById(car.getId()).get();

        CarDto verifyUpdateDto = CarDto.carDto()
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
    public void deleteTest() {
        Car existingCar = new Car();

        existingCar.setMake("A");
        existingCar.setModel("B");
        existingCar.setBodyType("C");
        existingCar.setYearOfProduction(2015);
        existingCar.setColor("D");
        existingCar.setMileage(100L);
        existingCar.setStatus(Status.AVAILABLE);
        existingCar.setAmount(5D);

        existingCar = carRepository.save(existingCar);

        String relativePath = "/cars/" + existingCar.getId();

        this.restTemplate.delete(relativePath, existingCar.getId());

        Optional<Car> updatedCar = this.carRepository.findById(existingCar.getId());

        Assertions.assertFalse(updatedCar.isPresent());
    }

    @Test
    public void filterTest() {

        Car car1 = new Car();

        car1.setMake("B");
        car1.setModel("B");
        car1.setBodyType("C");
        car1.setYearOfProduction(2015);
        car1.setColor("D");
        car1.setMileage(100L);
        car1.setStatus(Status.AVAILABLE);
        car1.setAmount(5D);

        car1 = carRepository.save(car1);

        Car car2 = new Car();

        car2.setMake("B");
        car2.setModel("B");
        car2.setBodyType("C");
        car2.setYearOfProduction(2015);
        car2.setColor("D");
        car2.setMileage(100L);
        car2.setStatus(Status.AVAILABLE);
        car2.setAmount(5D);

        car2 = carRepository.save(car2);

        Car car3 = new Car();

        car3.setMake("A");
        car3.setModel("c");
        car3.setBodyType("C");
        car3.setYearOfProduction(2015);
        car3.setColor("D");
        car3.setMileage(100L);
        car3.setStatus(Status.AVAILABLE);
        car3.setAmount(5D);

        car3 = carRepository.save(car3);

        List<Car> listToFilter = carRepository.findAll().stream()
                .filter(car -> car.getMake().contains("B"))
                .collect(Collectors.toList());

        List<CarDto> dtoListToFilter = listToFilter.stream().map(carMapper::toDto).collect(Collectors.toList());

        String relativePath = "/cars" + "?make=B";

        ResponseEntity<CarDto[]> responseEntity = this.restTemplate.getForEntity(url(relativePath), CarDto[].class);
        CarDto[] arrayToCheck = responseEntity.getBody();

        List<CarDto> listToCheck = Arrays.asList(arrayToCheck);

        Assertions.assertEquals(dtoListToFilter, listToCheck);

    }
}
