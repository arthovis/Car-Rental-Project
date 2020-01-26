package com.sda10.carrental.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String make;

    @NotNull
    private String model;

    @NotNull
    private String bodyType;

    @NotNull
    private Integer yearOfProduction;

    @NotNull
    private String color;

    @NotNull
    private Long mileage;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @NotNull
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return make.equals(car.make) &&
                model.equals(car.model) &&
                bodyType.equals(car.bodyType) &&
                yearOfProduction.equals(car.yearOfProduction) &&
                color.equals(car.color) &&
                mileage.equals(car.mileage) &&
                status == car.status &&
                amount.equals(car.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, bodyType, yearOfProduction, color, mileage, status, amount);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }

}
