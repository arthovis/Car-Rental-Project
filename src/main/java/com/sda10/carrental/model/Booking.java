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
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer client;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Rental rental;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private CarReturn carReturn;

    @NotNull
    @OneToOne
    private Branch rentalBranch;

    @NotNull
    @OneToOne
    private Branch returnBranch;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    private Double amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BookingStatus bookingStatus;

    public Booking() {
    }

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

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public CarReturn getCarReturn() {
        return carReturn;
    }

    public void setCarReturn(CarReturn carReturn) {
        this.carReturn = carReturn;
    }

    public void addRental(LocalDate dateFrom, Branch rentalBranch) {
        Rental rental = new Rental();
        rental.setRentalDate(dateFrom);
        rental.setBranch(rentalBranch);
        this.rental = rental;
    }

    public void addReturn(LocalDate dateTo, Branch returnBranch) {
        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(dateTo);
        carReturn.setBranch(returnBranch);
        this.carReturn = carReturn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Branch getRentalBranch() {
        return rentalBranch;
    }

    public void setRentalBranch(Branch rentalBranch) {
        this.rentalBranch = rentalBranch;
    }

    public Branch getReturnBranch() {
        return returnBranch;
    }

    public void setReturnBranch(Branch returnBranch) {
        this.returnBranch = returnBranch;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return dateOfBooking.equals(booking.dateOfBooking) &&
                client.equals(booking.client) &&
                car.equals(booking.car) &&
                rental.equals(booking.rental) &&
                carReturn.equals(booking.carReturn) &&
                rentalBranch.equals(booking.rentalBranch) &&
                returnBranch.equals(booking.returnBranch) &&
                dateFrom.equals(booking.dateFrom) &&
                dateTo.equals(booking.dateTo) &&
                amount.equals(booking.amount) &&
                bookingStatus == booking.bookingStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBooking, client, car, rental, carReturn, rentalBranch, returnBranch, dateFrom, dateTo, amount, bookingStatus);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateOfBooking=" + dateOfBooking +
                ", client=" + client +
                ", car=" + car +
                ", rental=" + rental +
                ", carReturn=" + carReturn +
                ", rentalBranch=" + rentalBranch +
                ", returnBranch=" + returnBranch +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", amount=" + amount +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

}
