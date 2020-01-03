package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.CarMapper;
import com.sda10.carrental.exception.NotFoundException;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Status;
import com.sda10.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/cars?{filters}")
    public ResponseEntity<CarDto> filterCars(@RequestParam(required = false) String make, @RequestParam(required = false) String model,
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

        if (filteredCar.isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new NotFoundException("Your car was not found");
        }


    }

}
