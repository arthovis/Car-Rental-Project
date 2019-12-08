package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CarRentalOfficeDtoTest extends UnitTest {

    @Test
    public  void givenTwoEqualCarRentalOffices_whenCompared_theResultIsTrue(){
        CarRentalOfficeDto carRentalOfficeDto1=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        CarRentalOfficeDto carRentalOfficeDto2=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesNames_whenCompared_theResultIsFalse(){
        CarRentalOfficeDto carRentalOfficeDto1=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("B")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        CarRentalOfficeDto carRentalOfficeDto2=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesInternetDomains_whenCompared_theResultIsFalse(){
        CarRentalOfficeDto carRentalOfficeDto1=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("C")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        CarRentalOfficeDto carRentalOfficeDto2=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesOwners_whenCompared_theResultIsFalse(){
        CarRentalOfficeDto carRentalOfficeDto1=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("D")
                .withContactAddress("D")
                .withLogoType("E");

        CarRentalOfficeDto carRentalOfficeDto2=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesContanctAdresses_whenCompared_theResultIsFalse(){
        CarRentalOfficeDto carRentalOfficeDto1=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("E")
                .withLogoType("E");

        CarRentalOfficeDto carRentalOfficeDto2=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public  void givenTwoDifferentCarRentalOfficesContanctLogoTypes_whenCompared_theResultIsFalse(){
        CarRentalOfficeDto carRentalOfficeDto1=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("F");

        CarRentalOfficeDto carRentalOfficeDto2=CarRentalOfficeDto
                .carRentalOfficeDto()
                .withId(1L)
                .withName("A")
                .withInternetDomain("B")
                .withOwner("C")
                .withContactAddress("D")
                .withLogoType("E");

        boolean comparisonResult=carRentalOfficeDto1.equals(carRentalOfficeDto2);

        Assertions.assertFalse(comparisonResult);
    }


}
