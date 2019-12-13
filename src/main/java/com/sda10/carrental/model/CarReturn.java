package com.sda10.carrental.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "car_return")
public class CarReturn {

    @Id
    @GeneratedValue
    private Long id;

//    @Column
//    @NotNull
//    private Employee employee;

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

    // equals() si hashCode() nu trebuie sa includa id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarReturn)) return false;
        CarReturn carReturn = (CarReturn) o;
        return Double.compare(carReturn.additionalPayment, additionalPayment) == 0 &&
                dateOfReturn.equals(carReturn.dateOfReturn) &&
                Objects.equals(comments, carReturn.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfReturn, additionalPayment, comments);
    }

    @Override
    public String toString() {
        return "CarReturn{" +
                "id=" + id +
                ", dateOfReturn=" + dateOfReturn +
                ", additionalPayment=" + additionalPayment +
                ", comments='" + comments + '\'' +
                '}';
    }
}
