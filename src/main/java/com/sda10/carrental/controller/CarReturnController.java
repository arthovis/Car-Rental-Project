package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarReturnDto;
import com.sda10.carrental.dto.CarReturnMapper;
import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.service.CarReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarReturnController {

    private CarReturnService carReturnService;
    private final CarReturnMapper carReturnMapper;

    @Autowired
    public CarReturnController(CarReturnService carReturnService, CarReturnMapper carReturnMapper) {
        this.carReturnService = carReturnService;
        this.carReturnMapper = carReturnMapper;
    }

    @PostMapping("/car-return")
    public ResponseEntity<CarReturnDto> createCarReturn(@RequestBody CarReturnDto carReturnDtoDetails) {

        CarReturn newCarReturn = carReturnMapper.toEntity(carReturnDtoDetails);
        CarReturn createdCarReturn = carReturnService.createCarReturn(newCarReturn);

        CarReturnDto response = carReturnMapper.toDto(createdCarReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/car-return")
    public List<CarReturnDto> getAllCarReturns() {

        List<CarReturn> carReturns = carReturnService.findAllCarReturn();

        return carReturns.stream()
                .map(carReturn -> carReturnMapper.toDto(carReturn))
                .collect(Collectors.toList());
    }

    @GetMapping("/car-return/{id}")
    public ResponseEntity<CarReturnDto> getCarReturnById(@PathVariable("id") Long id) {

        CarReturn carReturn = carReturnService.findCarReturnById(id);

        CarReturnDto response = carReturnMapper.toDto(carReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/car-return/{id}")
    public ResponseEntity<CarReturnDto> updateCarReturn(@PathVariable("id") Long id, @RequestBody CarReturnDto carReturnDtoDetails) {

        CarReturn carReturnToUpdate = carReturnMapper.toEntity(carReturnDtoDetails);
        CarReturn updatedCarReturn = carReturnService.updateCarReturn(id, carReturnToUpdate);

        CarReturnDto response = carReturnMapper.toDto(updatedCarReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/car-return/{id}")
    public ResponseEntity deleteCarReturn(@PathVariable("id") Long id){

        carReturnService.deleteCarReturn(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
