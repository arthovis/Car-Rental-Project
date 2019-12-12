package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

public class RentalDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualRentals_whenCompared_theResultIsTrue() {
        RentalDto rentalDto1 = RentalDto
                .rentalDto()
                .withId(1L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");


        RentalDto rentalDto2 = RentalDto
                .rentalDto()
                .withId(1L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");

        boolean comparisonResult = rentalDto1.equals(rentalDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public void givenTwoDifferentRentalIds_whenCompared_theResultIsFalse() {
        RentalDto rentalDto1 = RentalDto
                .rentalDto()
                .withId(1L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");


        RentalDto rentalDto2 = RentalDto
                .rentalDto()
                .withId(2L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");

        boolean comparisonResult = rentalDto1.equals(rentalDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentRentalDates_whenCompared_theResultIsFalse() {
        RentalDto rentalDto1 = RentalDto
                .rentalDto()
                .withId(1L)
                .withRentalDate(Date.valueOf(LocalDate.ofYearDay(1995, 5)))
                .withComments("Achitat");


        RentalDto rentalDto2 = RentalDto
                .rentalDto()
                .withId(2L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");

        boolean comparisonResult = rentalDto1.equals(rentalDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentRentalComments_whenCompared_theResultIsFalse() {
        RentalDto rentalDto1 = RentalDto
                .rentalDto()
                .withId(1L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Achitat");


        RentalDto rentalDto2 = RentalDto
                .rentalDto()
                .withId(1L)
                .withRentalDate(Date.valueOf(LocalDate.now()))
                .withComments("Nechitat");

        boolean comparisonResult = rentalDto1.equals(rentalDto2);

        Assertions.assertFalse(comparisonResult);
    }
}
