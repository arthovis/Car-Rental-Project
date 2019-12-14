package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.CarMapper;
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

    @Autowired
    private CarMapper carMapper;

    @PostMapping(value = "/cars")
    public CarDto createCar(@RequestBody CarDto carDetails) {

        Car car = carMapper.toEntity(carDetails);
        car = carService.createCar(car);

        return carMapper.toDto(car);
    }

    @GetMapping(value = "/cars/{id}")
    public CarDto findCarById(@PathVariable Long id) {

        Car carById = carService.getCarById(id);

        return carMapper.toDto(carById);
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody CarDto carDetails) {

        Car car = carMapper.toEntity(carDetails);
        carService.updateCar(id, car);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);

        return new ResponseEntity((HttpStatus.OK));
    }

}
