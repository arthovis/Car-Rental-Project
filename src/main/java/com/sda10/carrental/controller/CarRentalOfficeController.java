package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarRentalOfficeDto;
import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.service.CarRentalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarRentalOfficeController {

    @Autowired
    private CarRentalOfficeService carRentalOfficeService;

    @PostMapping(value = "/car-rental-offices")
    public CarRentalOfficeDto createCarRentalOffice() {

        CarRentalOffice carRentalOffice = carRentalOfficeService.createCarRentalOffice();

        return CarRentalOfficeDto.carRentalOfficeDto()
                .withId(carRentalOffice.getId())
                .withName(carRentalOffice.getName())
                .withInternetDomain(carRentalOffice.getInternetDomain())
                .withOwner(carRentalOffice.getOwner())
                .withContactAddress(carRentalOffice.getContactAddress())
                .withLogoType(carRentalOffice.getLogoType());
    }


}
