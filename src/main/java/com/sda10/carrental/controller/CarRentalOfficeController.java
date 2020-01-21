package com.sda10.carrental.controller;

import com.sda10.carrental.dto.BranchDto;
import com.sda10.carrental.dto.BranchMapper;
import com.sda10.carrental.dto.CarRentalOfficeDto;
import com.sda10.carrental.dto.CarRentalOfficeMapper;
import com.sda10.carrental.model.Branch;
import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.service.CarRentalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarRentalOfficeController {

    @Autowired
    private CarRentalOfficeService carRentalOfficeService;

    @Autowired
    private CarRentalOfficeMapper carRentalOfficeMapper;

    @Autowired
    private BranchMapper branchMapper;

    @PostMapping(value = "/car-rental-offices")
    public CarRentalOfficeDto createCarRentalOffice(@RequestBody CarRentalOfficeDto carRentalOfficeDetails) {

        CarRentalOffice carRentalOffice = carRentalOfficeMapper.toEntity(carRentalOfficeDetails);

        carRentalOffice = carRentalOfficeService.createCarRentalOffice(carRentalOffice);

        return carRentalOfficeMapper.toDto(carRentalOffice);
    }


    @GetMapping(value = "/car-rental-offices/{id}")
    public CarRentalOfficeDto findCarRentalOfficeById(@PathVariable Long id) {

        CarRentalOffice carRentalOfficeById = carRentalOfficeService.getCarRentalOfficeById(id);

        return carRentalOfficeMapper.toDto(carRentalOfficeById);
    }

    @GetMapping(value = "/car-rental-offices")
    public List<CarRentalOfficeDto> findAllCarRentalOffice() {

        List<CarRentalOffice> carAllRentalOffice = carRentalOfficeService.getAllCarRentalOffices();
        return carAllRentalOffice
                .stream()
                .map(carRentalOfficeMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/car-rental-offices/{id}")
    public ResponseEntity updateCarRentalOffice(@PathVariable Long id, @RequestBody CarRentalOfficeDto carRentalOfficeDetails) {

        CarRentalOffice carRentalOffice = carRentalOfficeMapper.toEntity(carRentalOfficeDetails);

        carRentalOfficeService.updateCarRentalOffice(id, carRentalOffice);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/car-rental-offices/{id}")
    public ResponseEntity deleteCarRentalOffice(@PathVariable Long id) {

        carRentalOfficeService.deleteCarRentalOffice(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/car-rental-offices/{id}/branches")
    public ResponseEntity<CarRentalOfficeDto> addBranchToCarRentalOffice(@PathVariable Long id, @RequestBody BranchDto branchDto) {
        Branch branch = branchMapper.toEntity(branchDto);
        CarRentalOffice updatedCarRentalOffice = carRentalOfficeService.updateCarRentalOfficeWithBranch(id, branch);
        CarRentalOfficeDto response = carRentalOfficeMapper.toDto(updatedCarRentalOffice);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/car-rental-offices/{id}/details")
    public ResponseEntity<CarRentalOfficeDto> deleteBranchFromCarRentalOffice(@PathVariable Long id, @RequestBody BranchDto branchDto) {
        Branch branch = branchMapper.toEntity(branchDto);
        CarRentalOffice updatedCarRentalOffice = carRentalOfficeService.updateCarRentalOfficeWithoutBranch(id, branch);
        CarRentalOfficeDto response = carRentalOfficeMapper.toDto(updatedCarRentalOffice);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
