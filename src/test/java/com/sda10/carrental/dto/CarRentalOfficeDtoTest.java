package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import com.sda10.carrental.model.JobPosition;
import com.sda10.carrental.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CarRentalOfficeDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualCarRentalOffices_whenCompared_theResultIsTrue() {
        List<BranchDto> branchDtos = createBranchDtoList();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);
        ;

        boolean comparisonResult = carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesNames_whenCompared_theResultIsFalse() {

        List<BranchDto> branchDtos = createBranchDtoList();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("B")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);
        ;

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesInternetDomains_whenCompared_theResultIsFalse() {

        List<BranchDto> branchDtos = createBranchDtoList();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("C")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesOwners_whenCompared_theResultIsFalse() {

        List<BranchDto> branchDtos = createBranchDtoList();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("D")
                .withLogoType("E")
                .withBranches(branchDtos);

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesContanctAdresses_whenCompared_theResultIsFalse() {

        List<BranchDto> branchDtos = createBranchDtoList();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("E")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarRentalOfficesLogoTypes_whenCompared_theResultIsFalse() {

        List<BranchDto> branchDtos = createBranchDtoList();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("F")
                .withBranches(branchDtos);

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("E")
                .withBranches(branchDtos);

        boolean comparisonResult = carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarRentalOfficesBranchLists_whenCompared_theResultIsFalse() {

        List<BranchDto> branchDtos = createBranchDtoList();
        List<BranchDto> branchDtos1 = createBranchDtoList1();

        CarRentalOfficeDto carRentalOfficeDto1 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("F")
                .withBranches(branchDtos);

        CarRentalOfficeDto carRentalOfficeDto2 = CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withContactAddress("D")
                .withOwner("C")
                .withLogoType("F")
                .withBranches(branchDtos1);

        boolean comparisonResult = carRentalOfficeDto1.equals(carRentalOfficeDto2);

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


    private List<BranchDto> createBranchDtoList() {
        List<EmployeeDto> employeeDtos = createEmployeeDtoList();

        List<CarDto> carDtos = createCarDtoList();

        BranchDto branchDto = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Victoriei")
                .withEmployees(employeeDtos)
                .withCar(carDtos);
        List<BranchDto> branchDtos = new ArrayList<>();
        branchDtos.add(branchDto);
        return branchDtos;
    }

    private List<BranchDto> createBranchDtoList1() {
        List<EmployeeDto> employeeDtos = createEmployeeDtoList();

        List<CarDto> carDtos = createCarDtoList();

        BranchDto branchDto = BranchDto.branchDto()
                .withId(1L)
                .withAddress("Calea Ferentari")
                .withEmployees(employeeDtos)
                .withCar(carDtos);
        List<BranchDto> branchDtos = new ArrayList<>();
        branchDtos.add(branchDto);
        return branchDtos;
    }
}
