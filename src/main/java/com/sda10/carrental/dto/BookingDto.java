package com.sda10.carrental.dto;

import com.sda10.carrental.model.CarReturn;

import java.time.LocalDate;
import java.util.Objects;

public class BookingDto {

    public Long id;
    public LocalDate dateOfBooking;
    public CustomerDto client;
    public CarDto car;
    public RentalDto dateFrom;
    public CarReturnDto dateTo;
    public Long amount;

    private BookingDto() {
    }

    public static BookingDto bookingDto() {
        return new BookingDto();
    }

    public BookingDto withId(Long id) {
        this.id = id;
        return this;
    }

    public BookingDto withDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
        return this;
    }

    public BookingDto withClient(CustomerDto client) {
        this.client = client;
        return this;
    }

    public BookingDto withCar(CarDto car) {
        this.car = car;
        return this;
    }

    public BookingDto withDateFrom(RentalDto dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public BookingDto withDateTo(CarReturnDto dateTo) {
        this.dateTo = dateTo;
        return this;
    }

//    public BookingDto withRentalBranch(CarRentalOfficeDto rentalBranch) {
//        this.rentalBranch = rentalBranch;
//        return this;
//    }
//
//    public BookingDto withReturnBranch(CarRentalOfficeDto returnBranch) {
//        this.returnBranch = returnBranch;
//        return this;
//    }

    public BookingDto withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateOfBooking, that.dateOfBooking) &&
                Objects.equals(client, that.client) &&
                Objects.equals(car, that.car) &&
                Objects.equals(dateFrom, that.dateFrom) &&
//                Objects.equals(dateTo, that.dateTo) &&
//                Objects.equals(rentalBranch, that.rentalBranch) &&
//                Objects.equals(returnBranch, that.returnBranch) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfBooking, client, car, dateFrom, amount);
    }
//    rentalBranch, returnBranch, dateTo,
}
