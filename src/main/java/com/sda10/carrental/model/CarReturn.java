package com.sda10.carrental.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "car_return")
public class CarReturn {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private LocalDate dateOfReturn;

//    @Column
//    @NotNull
//    private Booking booking;

    @Column
    private double additionalPayment;

    @Column
    private String comments;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public double getAdditionalPayment() {
        return additionalPayment;
    }

    public void setAdditionalPayment(double additionalPayment) {
        this.additionalPayment = additionalPayment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarReturn)) return false;
        CarReturn carReturn = (CarReturn) o;
        return Double.compare(carReturn.additionalPayment, additionalPayment) == 0 &&
                dateOfReturn.equals(carReturn.dateOfReturn) &&
                Objects.equals(comments, carReturn.comments) &&
                employee.equals(carReturn.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfReturn, additionalPayment, comments, employee);
    }

    @Override
    public String toString() {
        return "CarReturn{" +
                "id=" + id +
                ", employee=" + employee +
                ", dateOfReturn=" + dateOfReturn +
                ", additionalPayment=" + additionalPayment +
                ", comments='" + comments + '\'' +
                '}';
    }
}
