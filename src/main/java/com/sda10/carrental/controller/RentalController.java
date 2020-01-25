package com.sda10.carrental.controller;

import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.dto.EmployeeMapper;
import com.sda10.carrental.dto.RentalDto;
import com.sda10.carrental.dto.RentalMapper;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.model.Rental;
import com.sda10.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @PostMapping(value = "/rental")
    public RentalDto createRental(@RequestBody RentalDto rentalDetails) {

        Rental rental = rentalMapper.toEntity(rentalDetails);
        rental = rentalService.createRental(rental);

        return rentalMapper.toDto(rental);
    }

    @GetMapping(value = "/rental/{id}")
    public RentalDto findRentalById(@PathVariable Long id) {

        Rental rentalById = rentalService.findRentalById(id);

        return rentalMapper.toDto(rentalById);
    }

    @PutMapping(value = "/rental/{id}")
    public ResponseEntity updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDetails) {

        Rental rental = rentalMapper.toEntity(rentalDetails);
        rentalService.updateRental(id, rental);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/rental/{id}")
    public ResponseEntity deleteRental(@PathVariable Long id) {

        rentalService.deleteRental(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/rental/{id}/employees")
    public ResponseEntity<RentalDto> addEmployeeToRental(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {

        Employee employee = employeeMapper.toEntity(employeeDto);
        Rental updatedRental = rentalService.updateRentalWithEmployee(id, employee);
        RentalDto response = rentalMapper.toDto(updatedRental);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
