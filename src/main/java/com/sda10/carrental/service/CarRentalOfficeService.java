package com.sda10.carrental.service;

import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.repository.CarRentalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarRentalOfficeService {

    @Autowired
    private CarRentalOfficeRepository carRentalOfficeRepository;

    public CarRentalOffice createCarRentalOffice(CarRentalOffice carRentalOffice) {

        return carRentalOfficeRepository.save(carRentalOffice);

    }

    public CarRentalOffice getCarRentalOfficeById(Long id){
        return carRentalOfficeRepository.getOne(id);
    }


}
