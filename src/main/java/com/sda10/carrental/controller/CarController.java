package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.CarMapper;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Status;
import com.sda10.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @PostMapping(value = "/cars")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDetails) {

        Car car = carMapper.toEntity(carDetails);
        car = carService.createCar(car);

        CarDto response = carMapper.toDto(car);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable Long id) {

        Car carById = carService.getCarById(id);

        CarDto response = carMapper.toDto(carById);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarDto carDetails) {

        Car car = carMapper.toEntity(carDetails);
        car = carService.updateCar(id, car);

        CarDto response = carMapper.toDto(car);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {

        carService.deleteCar(id);

        return new ResponseEntity((HttpStatus.OK));
    }

    @GetMapping(value = "/cars")
    public List<CarDto> filterCars(@RequestParam(required = false) String make, @RequestParam(required = false) String model,
                                   @RequestParam(required = false) String bodyType, @RequestParam(required = false) Integer yearOfProduction,
                                   @RequestParam(required = false) String color, @RequestParam(required = false) Long mileage,
                                   @RequestParam(required = false) Status status, @RequestParam(required = false) Double amount) {

        CarDto carDto = CarDto.carDto().withMake(make)
                .withModel(model)
                .withBodyType(bodyType)
                .withYearOfProduction(yearOfProduction)
                .withColor(color)
                .withMileage(mileage)
                .withStatus(status)
                .withAmount(amount);

        List<Car> filteredCar = carService.carFilters(carDto);

        return filteredCar.stream().map(carMapper::toDto).collect(Collectors.toList());
    }
}
