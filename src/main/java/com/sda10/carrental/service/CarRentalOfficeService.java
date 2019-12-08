package com.sda10.carrental.service;

import com.sda10.carrental.model.CarRentalOffice;
import org.springframework.stereotype.Service;

@Service
public class CarRentalOfficeService {

    public CarRentalOffice createCarRentalOffice() {
        CarRentalOffice carRentalOffice = new CarRentalOffice();

        carRentalOffice.setId(1L);
        carRentalOffice.setName("A");
        carRentalOffice.setInternetDomain("B");
        carRentalOffice.setContactAddress("C");
        carRentalOffice.setOwner("D");
        carRentalOffice.setLogoType("E");

        return carRentalOffice;
    }


}
