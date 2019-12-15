package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import com.sda10.carrental.model.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CarReturnDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualCarReturnDto_whenCompared_theResultIsTrue() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withId(1L)
                .withEmployeeDto(buildEmployeeDto(1L, "popescu ion", "manager"))
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(33)
                .withComments("car");

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withId(1L)
                .withEmployeeDto(buildEmployeeDto(1L, "popescu ion", "manager"))
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(33)
                .withComments("car");

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertTrue(comparisonResult);
    }

    @Test
    public void givenTwoCarReturnDtoWithDifferentIds_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withEmployeeDto(buildEmployeeDto(1L, "popescu ion", "manager"));

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withEmployeeDto(buildEmployeeDto(2L, "popescu vasile", "manager"));

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoCarReturnDtoWithDifferentEmployees_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withId(1L);

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withId(2L);

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertFalse(comparisonResult);
    }



    @Test
    public void givenTwoCarReturnDtoWithDifferentDateOfReturn_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withDateOfReturn(LocalDate.of(2019, 11, 11));

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withDateOfReturn(LocalDate.of(2020, 11, 11));

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoCarReturnDtoWithDifferentComments_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withAdditionalPayment(33);

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withAdditionalPayment(44);

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoCarReturnDtoWithDifferentAdditionalPayment_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withComments("car1");

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withComments("car2");

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertFalse(comparisonResult);
    }

    public EmployeeDto buildEmployeeDto(Long id, String nameAndSurname, String jobPosition) {
        return EmployeeDto.employeeDto()
                .withId(id)
                .withNameAndSurname(nameAndSurname)
                .withJobPosition(jobPosition);
    }
}
