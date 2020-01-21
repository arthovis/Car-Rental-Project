package com.sda10.carrental.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "branch")
    List<Employee> employeeList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "branch_id")
    List<Car> availableCarsList = new ArrayList<>();

    @Column
    @NotNull
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Car> getAvailableCarsList() {
        return availableCarsList;
    }

    public void setAvailableCarsList(List<Car> availableCarsList) {
        this.availableCarsList = availableCarsList;
    }

    public void addCar(Car car) {
        availableCarsList.add(car);
    }

    public void removeCar(Car car) {
        availableCarsList.remove(car);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employee.setBranch(this);
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
        employee.setBranch(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id) &&
                Objects.equals(address, branch.address) &&
                Objects.equals(employeeList, branch.employeeList) &&
                Objects.equals(availableCarsList, branch.availableCarsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, employeeList, availableCarsList);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", employeeList=" + employeeList +
                ", availableCarsList=" + availableCarsList +
                '}';
    }
}
