package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarRentalOfficeDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarRentalController {

    @PostMapping(value = "/car-rental-offices")
    public CarRentalOfficeDto createCarRentalOffice() {

        return CarRentalOfficeDto.carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");
    }

}
