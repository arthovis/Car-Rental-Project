package com.sda10.carrental.dto;

import com.sda10.carrental.model.BookingStatus;

import java.time.LocalDate;
import java.util.Objects;

public class BookingDto {

    public Long id;
    public LocalDate dateOfBooking;
    public CustomerDto client;
    public CarDto car;
    public RentalDto dateFrom;
    //    public CarRentalOfficeDto rentalBranch;
//    public CarRentalOfficeDto returnBranch;
    public Double amount;
    public CarReturnDto carReturnDto;
    public BookingStatus bookingStatus;

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

//    public BookingDto withRentalBranch(CarRentalOfficeDto rentalBranch) {
//        this.rentalBranch = rentalBranch;
//        return this;
//    }
//
//    public BookingDto withReturnBranch(CarRentalOfficeDto returnBranch) {
//        this.returnBranch = returnBranch;
//        return this;
//    }

    public BookingDto withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public BookingDto withCarReturnDto(CarReturnDto carReturnDto) {
        this.carReturnDto = carReturnDto;
        return this;
    }

    public BookingDto withStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
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
                Objects.equals(amount, that.amount) &&
                Objects.equals(carReturnDto, that.carReturnDto) &&
                bookingStatus == that.bookingStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfBooking, client, car, dateFrom, amount, carReturnDto, bookingStatus);
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", dateOfBooking=" + dateOfBooking +
                ", client=" + client +
                ", car=" + car +
                ", dateFrom=" + dateFrom +
                ", amount=" + amount +
                ", carReturnDto=" + carReturnDto +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}
