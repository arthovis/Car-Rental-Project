package com.sda10.carrental.service;

import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.exception.NotFoundException;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(Long id) {
        return carRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found"));
    }

    @Transactional
    public Car updateCar(Long id, Car car) {
        Optional<Car> carToUpdate = carRepository.findById(id);
        if (carToUpdate.isPresent()) {
            car.setId(id);
            return carRepository.save(car);
        } else {
            throw new NotFoundException("Car not found");
        }
    }

    public void deleteCar(Long id) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
            carRepository.deleteById(id);
        } else {
            throw new NotFoundException("Car not found");
        }
    }

    public List<Car> carFilters(CarDto carDto) {


        List<Car> filterList = carRepository.findAll();
        Stream<Car> filteredCars = filterList.stream();

        if (carDto.make != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getMake().equals(carDto.make));
        }

        if (carDto.model != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getModel().equals(carDto.model));
        }

        if (carDto.bodyType != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getBodyType().equals(carDto.bodyType));
        }

        if (carDto.yearOfProduction != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getYearOfProduction().equals(carDto.yearOfProduction));
        }

        if (carDto.color != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getColor().equals(carDto.color));
        }

        if (carDto.mileage != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getMileage().equals(carDto.mileage));
        }

        if (carDto.status != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getStatus().equals(carDto.status));
        }

        if (carDto.amount != null) {
            filteredCars = filteredCars
                    .filter(car -> car.getAmount().equals(carDto.amount));
        }

        return filteredCars.collect(Collectors.toList());

    }
}
