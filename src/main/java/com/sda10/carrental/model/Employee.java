package com.sda10.carrental.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String nameAndSurname;

    @NotNull
    private String jobPosition;

//    @Column
//    private Branch branch;
//

    @OneToMany(mappedBy = "employee")
    private List<CarReturn> carReturns = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public List<CarReturn> getCarReturns() {
        return carReturns;
    }

    public void addCarReturn(CarReturn carReturn) {
        carReturns.add(carReturn);
        carReturn.setEmployee(this);
    }

    public void removeCarReturn(CarReturn carReturn) {
        carReturns.remove(carReturn);
        carReturn.setEmployee(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return nameAndSurname.equals(employee.nameAndSurname) &&
                jobPosition.equals(employee.jobPosition) &&
                Objects.equals(carReturns, employee.carReturns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAndSurname, jobPosition, carReturns);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                '}';
    }
}
