package com.sda10.carrental.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private LocalDate dateOfBooking;

    @NotNull
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer client;

    @NotNull
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

//    @NotNull
//    private CarRentalOffice rentalBranch;

//    @NotNull
//    private CarRentalOffice returnBranch;

    @NotNull
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

//    public CarRentalOffice getRentalBranch() {
//        return rentalBranch;
//    }
//
//    public void setRentalBranch(CarRentalOffice rentalBranch) {
//        this.rentalBranch = rentalBranch;
//    }
//
//    public CarRentalOffice getReturnBranch() {
//        return returnBranch;
//    }
//
//    public void setReturnBranch(CarRentalOffice returnBranch) {
//        this.returnBranch = returnBranch;
//    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(dateOfBooking, booking.dateOfBooking) &&
                Objects.equals(client, booking.client) &&
                Objects.equals(car, booking.car) &&
                Objects.equals(dateFrom, booking.dateFrom) &&
                Objects.equals(dateTo, booking.dateTo) &&
//                Objects.equals(rentalBranch, booking.rentalBranch) &&
//                Objects.equals(returnBranch, booking.returnBranch) &&
                Objects.equals(amount, booking.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfBooking, client, car, dateFrom, dateTo, amount);
    }
//    rentalBranch, returnBranch,
}
