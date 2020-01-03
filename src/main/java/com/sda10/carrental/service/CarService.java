package com.sda10.carrental.service;

import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void deleteCar(Long id) {
        Car existingCar = carRepository.findById(id).get();

        carRepository.delete(existingCar);
    }

    public List<Car> carFilters(CarDto carDto) {

        List<Car> filterList = new ArrayList<>();
        Stream<Car> filteredCars;

        if (!carDto.make.isEmpty()) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getMake().equals(carDto.make));
        } else {
            throw new RuntimeException();
        }

        if (!carDto.model.isEmpty()) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getModel().equals(carDto.model));
        } else {
            throw new RuntimeException();
        }

        if (!carDto.bodyType.isEmpty()) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getBodyType().equals(carDto.bodyType));
        } else {
            throw new RuntimeException();
        }

        if (carDto.yearOfProduction != null) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getYearOfProduction().equals(carDto.yearOfProduction));
        } else {
            throw new RuntimeException();
        }

        if (!carDto.color.isEmpty()) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getColor().equals(carDto.color));
        } else {
            throw new RuntimeException();
        }

        if (carDto.mileage != null) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getMileage().equals(carDto.mileage));
        } else {
            throw new RuntimeException();
        }

        if (!carDto.status.equals(null)) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getStatus().equals(carDto.status));
        } else {
            throw new RuntimeException();
        }

        if (carDto.amount != null) {
            filteredCars = filterList.stream()
                    .filter(car -> car.getAmount().equals(carDto.amount));
        } else {
            throw new RuntimeException();
        }

        return filteredCars.collect(Collectors.toList());

    }
}
