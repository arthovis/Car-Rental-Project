package com.sda10.carrental.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CarReturnDto {

    public Long id;
    public EmployeeDto employeeDto;
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

    public CarReturnDto withEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
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
                Objects.equals(employeeDto, that.employeeDto) &&
                Objects.equals(dateOfReturn, that.dateOfReturn) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeDto, dateOfReturn, additionalPayment, comments);
    }

    @Override
    public String toString() {
        return "CarReturnDto{" +
                "id=" + id +
                ", employeeDto=" + employeeDto +
                ", dateOfReturn=" + dateOfReturn +
                ", additionalPayment=" + additionalPayment +
                ", comments='" + comments + '\'' +
                '}';
    }
}
