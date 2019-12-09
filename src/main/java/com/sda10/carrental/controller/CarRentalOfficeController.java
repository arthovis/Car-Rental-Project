package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarRentalOfficeDto;
import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.service.CarRentalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarRentalOfficeController {

    @Autowired
    private CarRentalOfficeService carRentalOfficeService;

    @PostMapping(value = "/car-rental-offices")
    public CarRentalOfficeDto createCarRentalOffice(@RequestBody CarRentalOfficeDto carRentalOfficeDetails) {

        CarRentalOffice carRentalOffice = new CarRentalOffice();

        carRentalOffice.setName(carRentalOfficeDetails.name);
        carRentalOffice.setInternetDomain(carRentalOfficeDetails.internetDomain);
        carRentalOffice.setOwner(carRentalOfficeDetails.owner);
        carRentalOffice.setContactAddress(carRentalOfficeDetails.contactAddress);
        carRentalOffice.setLogoType(carRentalOfficeDetails.logoType);

        carRentalOffice = carRentalOfficeService.createCarRentalOffice(carRentalOffice);

        return CarRentalOfficeDto.carRentalOfficeDto()
                .withId(carRentalOffice.getId())
                .withName(carRentalOffice.getName())
                .withInternetDomain(carRentalOffice.getInternetDomain())
                .withOwner(carRentalOffice.getOwner())
                .withContactAddress(carRentalOffice.getContactAddress())
                .withLogoType(carRentalOffice.getLogoType());
    }


    @GetMapping(value = "/car-rental-offices/{id}")
    public CarRentalOfficeDto findCarRentalOfficeById(@PathVariable Long id) {

        CarRentalOffice carRentalOfficeById = carRentalOfficeService.getCarRentalOfficeById(id);

        return CarRentalOfficeDto.carRentalOfficeDto()
                .withId(carRentalOfficeById.getId())
                .withName(carRentalOfficeById.getName())
                .withInternetDomain(carRentalOfficeById.getInternetDomain())
                .withOwner(carRentalOfficeById.getOwner())
                .withContactAddress(carRentalOfficeById.getContactAddress())
                .withLogoType(carRentalOfficeById.getLogoType());

    }

    @PutMapping(value = "/car-rental-offices/{id}")
    public ResponseEntity updateCarRentalOffice(@PathVariable Long id, @RequestBody CarRentalOfficeDto carRentalOfficeDetails) {

        CarRentalOffice carRentalOffice = new CarRentalOffice();

        carRentalOffice.setName(carRentalOfficeDetails.name);
        carRentalOffice.setInternetDomain(carRentalOfficeDetails.internetDomain);
        carRentalOffice.setOwner(carRentalOfficeDetails.owner);
        carRentalOffice.setContactAddress(carRentalOfficeDetails.contactAddress);
        carRentalOffice.setLogoType(carRentalOfficeDetails.logoType);

        carRentalOfficeService.updateCarRentalOffice(id, carRentalOffice);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/car-rental-offices/{id}")
    public ResponseEntity deleteCarRentalOffice(@PathVariable Long id) {

        carRentalOfficeService.deleteCarRentalOffice(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
