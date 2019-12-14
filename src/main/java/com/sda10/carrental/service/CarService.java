package com.sda10.carrental.service;

import com.sda10.carrental.model.Car;
import com.sda10.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Car getCarById(Long id){
        return carRepository.getOne(id);
    }

    @Transactional
    public Car updateCar(Long id, Car car){
        Optional<Car> carToUpdate=carRepository.findById(id);
        if(carToUpdate.isPresent()){
            car.setId(id);
            return carRepository.save(car);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteCar(Long id){
        Car existingCar=carRepository.findById(id).get();

        carRepository.delete(existingCar);
    }
}
