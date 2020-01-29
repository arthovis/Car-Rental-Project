package com.sda10.carrental.dto;

import com.sda10.carrental.model.BookingStatus;

import java.time.LocalDate;
import java.util.Objects;

public class BookingDto {

    public Long id;
    public LocalDate dateOfBooking;
    public LocalDate dateFrom;
    public LocalDate dateTo;
    public BranchDto rentalBranchDto;
    public BranchDto returnBranchDto;
    public CustomerDto customerDto;
    public CarDto carDto;
    public RentalDto rentalDto;
    public CarReturnDto carReturnDto;
    public Double amount;
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

    public BookingDto withDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public BookingDto withDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public BookingDto withClientDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
        return this;
    }

    public BookingDto withCarDto(CarDto carDto) {
        this.carDto = carDto;
        return this;
    }

    public BookingDto withRentalBranchDto(BranchDto rentalBranchDto) {
        this.rentalBranchDto = rentalBranchDto;
        return this;
    }

    public BookingDto withReturnBranchDto(BranchDto returnBranchDto) {
        this.returnBranchDto = returnBranchDto;
        return this;
    }

    public BookingDto withRentalDto(RentalDto rentalDto) {
        this.rentalDto = rentalDto;
        return this;
    }

    public BookingDto withCarReturnDto(CarReturnDto carReturnDto) {
        this.carReturnDto = carReturnDto;
        return this;
    }

    public BookingDto withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public BookingDto withStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingDto)) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateOfBooking, that.dateOfBooking) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo) &&
                Objects.equals(rentalBranchDto, that.rentalBranchDto) &&
                Objects.equals(returnBranchDto, that.returnBranchDto) &&
                Objects.equals(customerDto, that.customerDto) &&
                Objects.equals(carDto, that.carDto) &&
                Objects.equals(rentalDto, that.rentalDto) &&
                Objects.equals(carReturnDto, that.carReturnDto) &&
                Objects.equals(amount, that.amount) &&
                bookingStatus == that.bookingStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfBooking, dateFrom, dateTo, rentalBranchDto, returnBranchDto, customerDto, carDto, rentalDto, carReturnDto, amount, bookingStatus);
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", dateOfBooking=" + dateOfBooking +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", rentalBranchDto=" + rentalBranchDto +
                ", returnBranchDto=" + returnBranchDto +
                ", customerDto=" + customerDto +
                ", carDto=" + carDto +
                ", rentalDto=" + rentalDto +
                ", carReturnDto=" + carReturnDto +
                ", amount=" + amount +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

}
