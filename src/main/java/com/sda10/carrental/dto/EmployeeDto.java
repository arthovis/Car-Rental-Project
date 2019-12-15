package com.sda10.carrental.dto;

import com.sda10.carrental.model.JobPosition;

import java.util.Objects;

public class EmployeeDto {

    public Long id;

    public String nameAndSurname;

    public JobPosition jobPosition;

    private EmployeeDto(){

    }

    public static EmployeeDto employeeDto(){
        return new EmployeeDto();
    }

    public EmployeeDto withId(Long id){
        this.id=id;
        return this;
    }

    public EmployeeDto withNameAndSurname(String nameAndSurname){
        this.nameAndSurname=nameAndSurname;
        return this;
    }

    public EmployeeDto withJobPosition(JobPosition jobPosition) {
        this.jobPosition=jobPosition;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameAndSurname, that.nameAndSurname) &&
                jobPosition == that.jobPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameAndSurname, jobPosition);
    }
}
