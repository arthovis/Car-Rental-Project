package com.sda10.carrental.dto;

import com.sda10.carrental.model.Employee;

import java.time.LocalDate;
import java.util.Objects;

public class CarReturnDto {

    public Long id;
    public Employee employee;
    public LocalDate dateOfReturn;
    //    public Booking booking;
    public double additionalPayment;
    public String comments;

    private CarReturnDto() {
    }

    public static CarReturnDto carReturnDto() {
        return new CarReturnDto();
    }

    public CarReturnDto withId(Long id) {
        this.id = id;
        return this;
    }

    public CarReturnDto withEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public CarReturnDto withDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
        return this;
    }

    public CarReturnDto withAdditionalPayment(double additionalPayment) {
        this.additionalPayment = additionalPayment;
        return this;
    }

    public CarReturnDto withComments(String comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarReturnDto)) return false;
        CarReturnDto that = (CarReturnDto) o;
        return Double.compare(that.additionalPayment, additionalPayment) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(dateOfReturn, that.dateOfReturn) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, dateOfReturn, additionalPayment, comments);
    }

    @Override
    public String toString() {
        return "CarReturnDto{" +
                "id=" + id +
                ", employee=" + employee +
                ", dateOfReturn=" + dateOfReturn +
                ", additionalPayment=" + additionalPayment +
                ", comments='" + comments + '\'' +
                '}';
    }
}
