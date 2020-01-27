package com.sda10.carrental.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Enumerated(EnumType.STRING)
    @NotNull
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

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

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
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
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(nameAndSurname, employee.nameAndSurname) &&
                jobPosition == employee.jobPosition;
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
