package com.sda10.carrental.dto;

import com.sda10.carrental.model.Status;

import java.util.Objects;

public class CarDto {

    public Long id;

    public String make;

    public String model;

    public String bodyType;

    public Integer yearOfProduction;

    public String color;

    public Long mileage;

    public Status status;

    public Double amount;

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

    public CarDto withYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
        return this;
    }


    public CarDto withColor(String color){
        this.color=color;
        return this;
    }

    public CarDto withMileage(Long  mileage){
        this.mileage=mileage;
        return this;
    }

    public CarDto withStatus(Status status) {
        this.status=status;
        return this;
    }

    public CarDto withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return Objects.equals(id, carDto.id) &&
                Objects.equals(make, carDto.make) &&
                Objects.equals(model, carDto.model) &&
                Objects.equals(bodyType, carDto.bodyType) &&
                Objects.equals(yearOfProduction, carDto.yearOfProduction) &&
                Objects.equals(color, carDto.color) &&
                Objects.equals(mileage, carDto.mileage) &&
                Objects.equals(status, carDto.status) &&
                Objects.equals(amount, carDto.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, bodyType, yearOfProduction, color, mileage, status, amount);
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", status=" + status +
                ", amount='" + amount + '\'' +
                '}';
    }
}


