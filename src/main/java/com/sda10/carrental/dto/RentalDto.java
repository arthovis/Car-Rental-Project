package com.sda10.carrental.dto;

import java.time.LocalDate;
import java.util.Objects;

public class RentalDto {

    public Long id;
    public LocalDate rentalDate;
    public String comments;
    public EmployeeDto employeeDto;
    public BranchDto branchDto;

    private RentalDto() {
    }

    public static RentalDto rentalDto() {
        return new RentalDto();
    }

    public RentalDto withId(Long id) {
        this.id = id;
        return this;
    }

    public RentalDto withRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
        return this;
    }

    public RentalDto withComments(String comments) {
        this.comments = comments;
        return this;
    }

    public RentalDto withEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
        return this;
    }

    public RentalDto withBranchDto(BranchDto branchDto) {
        this.branchDto = branchDto;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentalDto)) return false;
        RentalDto rentalDto = (RentalDto) o;
        return Objects.equals(id, rentalDto.id) &&
                Objects.equals(rentalDate, rentalDto.rentalDate) &&
                Objects.equals(comments, rentalDto.comments) &&
                Objects.equals(employeeDto, rentalDto.employeeDto) &&
                Objects.equals(branchDto, rentalDto.branchDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalDate, comments, employeeDto, branchDto);
    }

    @Override
    public String toString() {
        return "RentalDto{" +
                "id=" + id +
                ", rentalDate=" + rentalDate +
                ", comments='" + comments + '\'' +
                ", employeeDto=" + employeeDto +
                ", branchDto=" + branchDto +
                '}';
    }
}
