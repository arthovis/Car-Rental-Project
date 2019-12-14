package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import com.sda10.carrental.model.JobPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualEmployees_whenCompared_resultIsTrue() {
        EmployeeDto employeeDto1 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);
        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);

        boolean comparisonResult = employeeDto1.equals(employeeDto2);

        Assertions.assertTrue(comparisonResult);

    }

    @Test
    public void givenTwoDifferentEmployeesNamesAndSurnames_whenCompared_resultIsFalse() {
        EmployeeDto employeeDto1 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Paul Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);
        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);

        boolean comparisonResult = employeeDto1.equals(employeeDto2);

        Assertions.assertFalse(comparisonResult);
    }


    @Test
    public void givenTwoDifferentEmployeesJobPositions_whenCompared_resultIsFalse() {
        EmployeeDto employeeDto1 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);
        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.MANAGER);

        boolean comparisonResult = employeeDto1.equals(employeeDto2);

        Assertions.assertFalse(comparisonResult);

    }
}



