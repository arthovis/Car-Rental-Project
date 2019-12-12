package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarReturnDto;
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

    @Autowired
    public CarReturnController(CarReturnService carReturnService) {
        this.carReturnService = carReturnService;
    }

    @PostMapping("/car-return")
    public ResponseEntity<CarReturnDto> createCarReturn(@RequestBody CarReturnDto carReturnDtoDetails) {

        CarReturn newCarReturn = new CarReturn();
        newCarReturn.setDateOfReturn(carReturnDtoDetails.dateOfReturn);
        newCarReturn.setAdditionalPayment(carReturnDtoDetails.additionalPayment);
        newCarReturn.setComments(carReturnDtoDetails.comments);

        CarReturn createdCarReturn = carReturnService.createCarReturn(newCarReturn);

        CarReturnDto response = CarReturnDto.carReturnDto()
                .withId(createdCarReturn.getId())
                .withDateOfReturn(createdCarReturn.getDateOfReturn())
                .withAdditionalPayment(createdCarReturn.getAdditionalPayment())
                .withComments(createdCarReturn.getComments());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/car-return")
    public List<CarReturnDto> getAllCarReturns() {

        List<CarReturn> carReturns = carReturnService.findAllCarReturn();

        return carReturns.stream()
                .map(carReturn -> CarReturnDto.carReturnDto()
                        .withId(carReturn.getId())
                        .withDateOfReturn(carReturn.getDateOfReturn())
                        .withAdditionalPayment(carReturn.getAdditionalPayment())
                        .withComments(carReturn.getComments()))
                .collect(Collectors.toList());
    }

    @GetMapping("/car-return/{id}")
    public ResponseEntity<CarReturnDto> getCarReturnById(@PathVariable("id") Long id) {

        CarReturn carReturn = carReturnService.findCarReturnById(id);

        CarReturnDto response = CarReturnDto.carReturnDto()
                .withId(carReturn.getId())
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/car-return/{id}")
    public ResponseEntity<CarReturnDto> updateCarReturn(@PathVariable("id") Long id, @RequestBody CarReturnDto carReturnDtoDetails) {

        CarReturn carReturnToUpdate = new CarReturn();
        carReturnToUpdate.setDateOfReturn(carReturnDtoDetails.dateOfReturn);
        carReturnToUpdate.setAdditionalPayment(carReturnDtoDetails.additionalPayment);
        carReturnToUpdate.setComments(carReturnDtoDetails.comments);

        CarReturn updatedCarReturn = carReturnService.updateCarReturn(id, carReturnToUpdate);

        CarReturnDto response = CarReturnDto.carReturnDto()
                .withId(updatedCarReturn.getId())
                .withDateOfReturn(updatedCarReturn.getDateOfReturn())
                .withAdditionalPayment(updatedCarReturn.getAdditionalPayment())
                .withComments(updatedCarReturn.getComments());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/car-return/{id}")
    public ResponseEntity deleteCarReturn(@PathVariable("id") Long id){

        carReturnService.deleteCarReturn(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
