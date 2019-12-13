package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(value = "/cars")
    public CarDto createCar(@RequestBody CarDto carDetails) {
        Car car = new Car();

        car.setMake(carDetails.make);
        car.setModel(carDetails.model);
        car.setBodyType(carDetails.bodyType);
        car.setYearOfProduction(carDetails.yearOfProduction);
        car.setColor(carDetails.color);
        car.setMileage(carDetails.mileage);
        car.setStatus(carDetails.status);
        car.setAmount(carDetails.amount);

        car = carService.createCar(car);

        return CarDto.carDto()
                .withId(car.getId())
                .withMake(car.getMake())
                .withModel(car.getModel())
                .withBodyType(car.getBodyType())
                .withYearOfProduction(car.getYearOfProduction())
                .withColor(car.getColor())
                .withMileage(car.getMileage())
                .withStatus(car.getStatus())
                .withAmount(car.getAmount());
    }

    @GetMapping(value = "/cars/{id}")
    public CarDto findCarById(@PathVariable Long id) {
        Car carById = carService.getCarById(id);

        return CarDto.carDto()
                .withId(carById.getId())
                .withMake(carById.getMake())
                .withModel(carById.getModel())
                .withBodyType(carById.getBodyType())
                .withYearOfProduction(carById.getYearOfProduction())
                .withColor(carById.getColor())
                .withMileage(carById.getMileage())
                .withStatus(carById.getStatus())
                .withAmount(carById.getAmount());
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody CarDto carDetails) {
        Car car = new Car();

        car.setMake(carDetails.make);
        car.setModel(carDetails.model);
        car.setBodyType(carDetails.bodyType);
        car.setYearOfProduction(carDetails.yearOfProduction);
        car.setColor(carDetails.color);
        car.setMileage(carDetails.mileage);
        car.setStatus(carDetails.status);
        car.setAmount(carDetails.amount);

        carService.updateCar(id, car);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);

        return new ResponseEntity((HttpStatus.OK));
    }

}
