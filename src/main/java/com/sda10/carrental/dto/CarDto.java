package com.sda10.carrental.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CarDto {

    public Long id;

    public String make;

    public String model;

    public String bodyType;

    public int yearOfProduction;

    public String color;

    public String mileage;

    public String status;

    public String amount;

    private CarDto() {

    }

    public static CarDto carDto() {
        return new CarDto();
    }

    public CarDto withId(Long id) {
        this.id = id;
        return this;
    }

    public CarDto withMake(String make) {
        this.make = make;
        return this;
    }

    public CarDto withModel(String model){
        this.model=model;
        return this;
    }

    public  CarDto withBodyType(String bodyType){
        this.bodyType=bodyType;
        return this;
    }

    public  CarDto withYearOfProduction(int yearOfProduction){
        this.bodyType=bodyType;
        return this;
    }

    public CarDto withColor(String color){
        this.color=color;
        return this;
    }

    public CarDto withMileage(String  mileage){
        this.mileage=mileage;
        return this;
    }

    public CarDto withStatus(String status){
        this.status=mileage;
        return this;
    }

    public CarDto withAmount(String amount){
        this.amount=amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return yearOfProduction == carDto.yearOfProduction &&
                Objects.equals(id, carDto.id) &&
                Objects.equals(make, carDto.make) &&
                Objects.equals(model, carDto.model) &&
                Objects.equals(bodyType, carDto.bodyType) &&
                Objects.equals(color, carDto.color) &&
                Objects.equals(mileage, carDto.mileage) &&
                Objects.equals(status, carDto.status) &&
                Objects.equals(amount, carDto.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, bodyType, yearOfProduction, color, mileage, status, amount);
    }
}


