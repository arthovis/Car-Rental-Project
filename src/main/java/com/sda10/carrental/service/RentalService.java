package com.sda10.carrental.service;

import com.sda10.carrental.model.Employee;
import com.sda10.carrental.model.Rental;
import com.sda10.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public Rental updateRentalWithEmployee(Long id, Employee employee) {
        Optional<Rental> rentalToUpdate = rentalRepository.findById(id);

        if (rentalToUpdate.isPresent()) {
            Rental rental = rentalToUpdate.get();
            rental.setEmployee(employee);
            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Rental could not be updated");
        }
    }

    public List<Rental> getAllRentals(Integer pageIndex, Integer pageSize) {
        Pageable paging = PageRequest.of(pageIndex, pageSize);

        Page<Rental> pagedResult = rentalRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
