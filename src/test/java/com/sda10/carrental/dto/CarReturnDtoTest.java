package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import com.sda10.carrental.model.JobPosition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.sda10.carrental.model.JobPosition.MANAGER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarReturnDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualCarReturnDto_whenCompared_theResultIsTrue() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withId(1L)
                .withEmployeeDto(buildEmployeeDto(1L, "popescu ion", MANAGER))
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(33)
                .withComments("car")
                .withBranchDto(buildLightBranchDto(1L, "Calea Victoriei"));

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withId(1L)
                .withEmployeeDto(buildEmployeeDto(1L, "popescu ion", MANAGER))
                .withDateOfReturn(LocalDate.of(2019, 11, 11))
                .withAdditionalPayment(33)
                .withComments("car")
                .withBranchDto(buildLightBranchDto(1L, "Calea Victoriei"));

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertTrue(comparisonResult);
    }

    @Test
    public void givenTwoCarReturnDtoWithDifferentIds_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withEmployeeDto(buildEmployeeDto(1L, "popescu ion", MANAGER));

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withEmployeeDto(buildEmployeeDto(2L, "popescu vasile", MANAGER));

        boolean comparisonResult = carReturnDto1.equals(carReturnDto2);

        assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoCarReturnDtoWithDifferentBranch_whenCompared_theResultIsFalse() {

        CarReturnDto carReturnDto1 = CarReturnDto.carReturnDto()
                .withBranchDto(buildLightBranchDto(1L, "Calea Victoriei"));

        CarReturnDto carReturnDto2 = CarReturnDto.carReturnDto()
                .withBranchDto(buildLightBranchDto(1L, "Floreasca"));

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

    public EmployeeDto buildEmployeeDto(Long id, String nameAndSurname, JobPosition jobPosition) {
        return EmployeeDto.employeeDto()
                .withId(id)
                .withNameAndSurname(nameAndSurname)
                .withJobPosition(jobPosition);
    }

    public BranchDto buildLightBranchDto(Long id, String address) {
        return BranchDto.branchDto()
                .withId(id)
                .withAddress(address);
    }
}
