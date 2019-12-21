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

    @NotNull
    private LocalDate dateOfReturn;

    private double additionalPayment;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public CarReturn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarReturn)) return false;
        CarReturn carReturn = (CarReturn) o;
        return Double.compare(carReturn.additionalPayment, additionalPayment) == 0 &&
                dateOfReturn.equals(carReturn.dateOfReturn) &&
                Objects.equals(comments, carReturn.comments) &&
                Objects.equals(employee, carReturn.employee) &&
                Objects.equals(branch, carReturn.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfReturn, additionalPayment, comments, employee, branch);
    }

    @Override
    public String toString() {
        return "CarReturn{" +
                "id=" + id +
                ", dateOfReturn=" + dateOfReturn +
                ", additionalPayment=" + additionalPayment +
                ", comments='" + comments + '\'' +
                ", employee=" + employee +
                ", branch=" + branch +
                '}';
    }
}
