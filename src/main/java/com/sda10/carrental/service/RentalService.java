package com.sda10.carrental.service;

import com.sda10.carrental.model.Rental;
import com.sda10.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Transactional
    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }


    public Rental findRentalById(Long id) {
        return rentalRepository.getOne(id);
    }

    public Rental updateRental(Long id, Rental rental) {
        Optional<Rental> rentalToUpdate = rentalRepository.findById(id);

        if (rentalToUpdate.isPresent()) {
            rental.setId(id);
            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteRental(Long id) {
        Rental existingRental = rentalRepository.findById(id).get();

        rentalRepository.delete(existingRental);
    }

}