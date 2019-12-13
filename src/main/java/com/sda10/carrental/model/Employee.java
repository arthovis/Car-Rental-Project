package com.sda10.carrental.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="employee")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(nameAndSurname, employee.nameAndSurname) &&
                Objects.equals(jobPosition, employee.jobPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAndSurname, jobPosition);
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
