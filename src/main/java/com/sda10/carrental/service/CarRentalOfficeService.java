package com.sda10.carrental.service;

import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.repository.CarRentalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarRentalOfficeService {

    @Autowired
    private CarRentalOfficeRepository carRentalOfficeRepository;

    public CarRentalOffice createCarRentalOffice() {
        CarRentalOffice carRentalOffice = new CarRentalOffice();

        carRentalOffice.setName("A");
        carRentalOffice.setInternetDomain("B");
        carRentalOffice.setContactAddress("C");
        carRentalOffice.setOwner("D");
        carRentalOffice.setLogoType("E");

        carRentalOffice = carRentalOfficeRepository.save(carRentalOffice);

        return carRentalOffice;
    }


}
