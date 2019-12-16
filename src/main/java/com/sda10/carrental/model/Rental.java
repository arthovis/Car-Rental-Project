package com.sda10.carrental.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private LocalDate rentalDate;

    @Column
    @NotNull
    private String comments;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
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
        if (!(o instanceof Rental)) return false;
        Rental rental = (Rental) o;
        return rentalDate.equals(rental.rentalDate) &&
                Objects.equals(comments, rental.comments) &&
                employee.equals(rental.employee) &&
                branch.equals(rental.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalDate, comments, employee, branch);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", rentalDate=" + rentalDate +
                ", comments='" + comments + '\'' +
                ", employee=" + employee +
                ", branch=" + branch +
                '}';
    }
}
