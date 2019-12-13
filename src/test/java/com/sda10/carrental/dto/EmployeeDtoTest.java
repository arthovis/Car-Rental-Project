package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualEmployees_whenCompared_resultIsTrue() {
        EmployeeDto employeeDto1 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition("Manager");
        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition("Manager");

        boolean comparisonResult = employeeDto1.equals(employeeDto2);

        Assertions.assertTrue(comparisonResult);

    }

    @Test
    public void givenTwoDifferentEmployeesNamesAndSurnames_whenCompared_resultIsFalse() {
        EmployeeDto employeeDto1 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Paul Nebunu")
                .withJobPosition("Manager");
        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition("Manager");

        boolean comparisonResult = employeeDto1.equals(employeeDto2);

        Assertions.assertFalse(comparisonResult);
    }


    @Test
    public void givenTwoDifferentEmployeesJobPositions_whenCompared_resultIsFalse() {
        EmployeeDto employeeDto1 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition("Manager");
        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition("Employee");

        boolean comparisonResult = employeeDto1.equals(employeeDto2);

        Assertions.assertFalse(comparisonResult);

    }
}



