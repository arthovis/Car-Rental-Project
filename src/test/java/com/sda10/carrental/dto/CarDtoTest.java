package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualCars_whenCompared_resultIsTrue(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsMakes_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("B")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsModels_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("C")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsBodyTypes_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("D")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsYearsOfProduction_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2000)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsColors_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("E")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsMileages_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(101L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsStatuses_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("G")
                .withAmount("G");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCarsAmounts_whenCompared_resultIsFalse(){
        CarDto carDto1= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("F")
                .withAmount("H");

        CarDto carDto2= CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(2005)
                .withColor("D")
                .withMileage(100L)
                .withStatus("Z")
                .withAmount("G");

        boolean comparisonResult=carDto1.equals(carDto2);

        Assertions.assertFalse(comparisonResult);
    }
}
