package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BookingDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualBookings_whenCompared_resultIsTrue() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public void givenBookingWithTwoDifferentDateOfBooking_whenCompared_ResultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.of(2018, 12, 13))
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentClient_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("B")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentCar_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("A")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentDateFrom_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 19))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentDateTo_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 24))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentRentalBranch_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("D")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentReturnBranch_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("C")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentAmount_whenCompared_resultIsFalse() {
        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100L);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
//                .withClient("A")
//                .withCar("B")
                .withDateFrom(LocalDate.of(2019, 12, 20))
                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(1000L);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }
}
