package com.sda10.carrental.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BranchDto {

    public Long id;
    public String address;
    public List<EmployeeDto> employeeList = new ArrayList<>();
    public List<CarDto> availableCarsList = new ArrayList<>();

    private BranchDto() {
    }

    public static BranchDto branchDto() {
        return new BranchDto();
    }

    public BranchDto withId(Long id) {
        this.id = id;
        return this;
    }

    public BranchDto withAddress(String address) {
        this.address = address;
        return this;
    }

    public BranchDto withEmployees(List<EmployeeDto> employeeList) {
        this.employeeList = employeeList;
        return this;
    }

    public BranchDto withCar(List<CarDto> availableCarsList) {
        this.availableCarsList = availableCarsList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchDto branchDto = (BranchDto) o;
        return Objects.equals(id, branchDto.id) &&
                Objects.equals(address, branchDto.address) &&
                Objects.equals(employeeList, branchDto.employeeList) &&
                Objects.equals(availableCarsList, branchDto.availableCarsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, employeeList, availableCarsList);
    }
}
