package com.sda10.carrental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarRentalController {

    @PostMapping(value = "/car-rental-offices")
    public Long createCarRentalOffice() {
        return 1L;
    }

}
