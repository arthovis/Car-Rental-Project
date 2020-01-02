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

    public List<Car> carFilters() {

        List<Car> filterList = new ArrayList<>();

        return filterList.stream()
                .filter(car -> car.getMake().equals(CarDto.carDto().make))
                .filter(car -> car.getModel().equals(CarDto.carDto().model))
                .filter(car -> car.getBodyType().equals(CarDto.carDto().bodyType))
                .filter(car -> car.getYearOfProduction().equals(CarDto.carDto().yearOfProduction))
                .filter(car -> car.getColor().equals(CarDto.carDto().color))
                .filter(car -> car.getMileage().equals(CarDto.carDto().mileage))
                .filter(car -> car.getStatus().equals(CarDto.carDto().status))
                .filter(car -> car.getAmount().equals(CarDto.carDto().amount))
                .collect(Collectors.toList());

    }
}
