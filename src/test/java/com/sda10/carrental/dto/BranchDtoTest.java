package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import com.sda10.carrental.model.JobPosition;
import com.sda10.carrental.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BranchDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualBranchDtos_whenCompared_theResultIsTrue() {
        List<EmployeeDto> employeeDtos = createEmployeeDtoList();

        List<CarDto> carDtos = createCarDtoList();

        BranchDto branchDto1 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        BranchDto branchDto2 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        boolean comparisonResult = branchDto1.equals(branchDto2);

        Assertions.assertTrue(comparisonResult);

    }

    @Test
    public void givenTwoDifferentBranchIds_whenCompared_theResultIsFalse() {
        List<EmployeeDto> employeeDtos = createEmployeeDtoList();

        List<CarDto> carDtos = createCarDtoList();

        BranchDto branchDto1 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        BranchDto branchDto2 = BranchDto.branchDto()
                .withId(2L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        boolean comparisonResult = branchDto1.equals(branchDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentBranchAddresses_whenCompared_theResultIsFalse() {

        List<EmployeeDto> employeeDtos = createEmployeeDtoList();

        List<CarDto> carDtos = createCarDtoList();

        BranchDto branchDto1 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        BranchDto branchDto2 = BranchDto.branchDto()
                .withId(2L)
                .withAddress("Calea Ferentari")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        boolean comparisonResult = branchDto1.equals(branchDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentBranchEmployeeLists_whenCompared_theResultIsFalse() {

        EmployeeDto employeeDto = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);

        EmployeeDto employeeDto2 = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Paul Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(employeeDto);


        List<EmployeeDto> employeeDtos1 = new ArrayList<>();
        employeeDtos1.add(employeeDto2);

        List<CarDto> carDtos = createCarDtoList();

        BranchDto branchDto1 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        BranchDto branchDto2 = BranchDto.branchDto()
                .withId(2L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos1)
                .withCar(carDtos);

        boolean comparisonResult = branchDto1.equals(branchDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoEqualBranchCarLists_whenCompared_theResultIsTrue() {
        List<EmployeeDto> employeeDtos = createEmployeeDtoList();

        CarDto carDto1 = CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus(Status.AVAILABLE)
                .withAmount(5D);

        CarDto carDto2 = CarDto.carDto()
                .withId(1L)
                .withMake("B")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus(Status.AVAILABLE)
                .withAmount(5D);

        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(carDto1);

        List<CarDto> carDtos1 = new ArrayList<>();
        carDtos.add(carDto2);

        BranchDto branchDto1 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);

        BranchDto branchDto2 = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos1);

        boolean comparisonResult = branchDto1.equals(branchDto2);

        Assertions.assertFalse(comparisonResult);

    }

    private List<CarDto> createCarDtoList() {
        CarDto carDto1 = CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus(Status.AVAILABLE)
                .withAmount(5D);


        List<CarDto> carDtos = new ArrayList<>();
        carDtos.add(carDto1);
        return carDtos;
    }

    private List<EmployeeDto> createEmployeeDtoList() {
        EmployeeDto employeeDto = EmployeeDto
                .employeeDto()
                .withId(1L)
                .withNameAndSurname("Mircea Nebunu")
                .withJobPosition(JobPosition.EMPLOYEE);

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(employeeDto);
        return employeeDtos;
    }
}
