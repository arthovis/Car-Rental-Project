package com.sda10.carrental.controller;

import com.sda10.carrental.dto.RentalDto;
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

    @PostMapping(value = "/rental")
    public RentalDto createRental(@RequestBody RentalDto rentalDetails) {

        Rental rental = new Rental();

        rental.setRentalDate(rentalDetails.rentalDate);
        rental.setComments(rentalDetails.comments);

        rental = rentalService.createRental(rental);

        return RentalDto.rentalDto()
                .withId(rental.getId())
                .withRentalDate(rental.getRentalDate())
                .withComments(rental.getComments());
    }

    @GetMapping(value = "/rental/{id}")
    public RentalDto findRentalById(@PathVariable Long id) {
        Rental rentalById = rentalService.findRentalById(id);

        return RentalDto.rentalDto()
                .withId(rentalById.getId())
                .withRentalDate(rentalById.getRentalDate())
                .withComments(rentalById.getComments());
    }

    @PutMapping(value = "/rental/{id}")
    public ResponseEntity updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDetails) {

        Rental rental = new Rental();

        rental.setRentalDate(rentalDetails.rentalDate);
        rental.setComments(rentalDetails.comments);

        rentalService.updateRental(id, rental);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/rental/{id}")
    public ResponseEntity deleteRental(@PathVariable Long id) {

        rentalService.deleteRental(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
