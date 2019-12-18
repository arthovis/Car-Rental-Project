package com.sda10.carrental.service;

import com.sda10.carrental.exception.NotFoundException;
import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.repository.CarReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarReturnService {

    private CarReturnRepository carReturnRepository;

    @Autowired
    public CarReturnService(CarReturnRepository carReturnRepository) {
        this.carReturnRepository = carReturnRepository;
    }

    public CarReturn createCarReturn(CarReturn newCarReturn) {
        return carReturnRepository.save(newCarReturn);
    }

    public List<CarReturn> findAllCarReturn() {
        return carReturnRepository.findAll();
    }

    public CarReturn findCarReturnById(Long id) {
        return carReturnRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("carReturn not found"));
    }

    public CarReturn updateCarReturn(Long id, CarReturn updatedCarReturn) {
        Optional<CarReturn> carReturnToUpdate = carReturnRepository.findById(id);
        if (carReturnToUpdate.isPresent()) {
            updatedCarReturn.setId(id);
            return carReturnRepository.save(updatedCarReturn);
        } else {
            throw new NotFoundException("carReturn not found");
        }
    }

    public void deleteCarReturn(Long id){
        Optional<CarReturn> carReturnToDelete = carReturnRepository.findById(id);
        if (carReturnToDelete.isPresent()) {
            carReturnRepository.deleteById(id);
        } else {
            throw new NotFoundException("carReturn not found");
        }
    }

}
