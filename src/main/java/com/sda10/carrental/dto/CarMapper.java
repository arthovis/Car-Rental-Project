package com.sda10.carrental.dto;

import com.sda10.carrental.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car toEntity(CarDto carDto) {
        Car car = new Car();

        car.setId(carDto.id);
        car.setMake(carDto.make);
        car.setModel(carDto.model);
        car.setBodyType(carDto.bodyType);
        car.setYearOfProduction(carDto.yearOfProduction);
        car.setColor(carDto.color);
        car.setMileage(carDto.mileage);
        car.setStatus(carDto.status);
        car.setAmount(carDto.amount);

        return car;
    }

    public CarDto toDto(Car car) {
        return CarDto.carDto()
                .withId(car.getId())
                .withMake(car.getMake())
                .withModel(car.getModel())
                .withBodyType(car.getBodyType())
                .withYearOfProduction(car.getYearOfProduction())
                .withColor(car.getColor())
                .withMileage(car.getMileage())
                .withStatus(car.getStatus())
                .withAmount(car.getAmount());
    }

}
