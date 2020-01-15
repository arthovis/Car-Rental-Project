package com.sda10.carrental.service;

import com.sda10.carrental.model.Branch;
import com.sda10.carrental.model.CarRentalOffice;
import com.sda10.carrental.repository.CarRentalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarRentalOfficeService {

    @Autowired
    private CarRentalOfficeRepository carRentalOfficeRepository;

    @Transactional
    public CarRentalOffice createCarRentalOffice(CarRentalOffice carRentalOffice) {

        return carRentalOfficeRepository.save(carRentalOffice);

    }

    public CarRentalOffice getCarRentalOfficeById(Long id) {
        return carRentalOfficeRepository.getOne(id);
    }

    public CarRentalOffice updateCarRentalOffice(Long id, CarRentalOffice carRentalOffice) {
        Optional<CarRentalOffice> carRentalOfficeToUpdate = carRentalOfficeRepository.findById(id);
        if (carRentalOfficeToUpdate.isPresent()) {
            carRentalOffice.setId(id);
            return carRentalOfficeRepository.save(carRentalOffice);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteCarRentalOffice(Long id) {
        CarRentalOffice existingCarRentalOffice = carRentalOfficeRepository.findById(id).get();

        carRentalOfficeRepository.delete(existingCarRentalOffice);
    }

    public List<CarRentalOffice> getAllCarRentalOffices() {
        return carRentalOfficeRepository.findAll();
    }

    public CarRentalOffice updateCarRentalOfficeWithBranch(Long id, Branch branch) {
        Optional<CarRentalOffice> CarRentalOfficeToUpdate = carRentalOfficeRepository.findById(id);

        if (CarRentalOfficeToUpdate.isPresent()) {
            CarRentalOffice carRentalOffice = CarRentalOfficeToUpdate.get();
            carRentalOffice.addBranch(branch);
            return carRentalOfficeRepository.save(carRentalOffice);
        } else {
            throw new RuntimeException("Rental office could not be updated");
        }
    }

    public CarRentalOffice updateCarRentalOfficeWithoutBranch(Long id, Branch branch) {
        Optional<CarRentalOffice> CarRentalOfficeToUpdate = carRentalOfficeRepository.findById(id);

        if (CarRentalOfficeToUpdate.isPresent()) {
            CarRentalOffice carRentalOffice = CarRentalOfficeToUpdate.get();
            carRentalOffice.removeBranch(branch);
            return carRentalOfficeRepository.save(carRentalOffice);
        } else {
            throw new RuntimeException("Rental office could not be removed");
        }
    }

}
