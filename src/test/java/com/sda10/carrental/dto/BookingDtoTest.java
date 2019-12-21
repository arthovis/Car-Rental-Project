package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import com.sda10.carrental.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BookingDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualBookings_whenCompared_resultIsTrue() {
        CustomerDto customer = getCustomerDto();

        CarDto car = getCarDto();

        RentalDto rental = getRental();

        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public void givenBookingWithTwoDifferentDateOfBooking_whenCompared_ResultIsFalse() {
        CustomerDto customer = getCustomerDto();
        CarDto car = getCarDto();

        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.of(2018, 12, 13))
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentClient_whenCompared_resultIsFalse() {
        CustomerDto customer1 = getCustomerDto();
        CustomerDto customer2 = getCustomerDto2();
        CarDto car = getCarDto();

        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer1)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer2)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }


    @Test
    public void givenTwoBookingWithDifferentCar_whenCompared_resultIsFalse() {
        CustomerDto customer = getCustomerDto();
        CarDto car = getCarDto();
        CarDto car2 = getCarDto2();

        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car2)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoBookingWithDifferentDateFrom_whenCompared_resultIsFalse() {
        CustomerDto customer = getCustomerDto();
        CarDto car = getCarDto();

        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental2())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }


//    @Test
//    public void givenTwoBookingWithDifferentDateTo_whenCompared_resultIsFalse() {
//        CustomerDto customer = getCustomerDto();
//        CarDto car = getCarDto();
//
//        BookingDto bookingDto1 = BookingDto
//                .bookingDto()
//                .withId(1L)
//                .withDateOfBooking(LocalDate.now())
//                .withClient(customer)
//                .withCar(car)
//                .withDateFrom(LocalDate.of(2019, 12, 20))
//                .withDateTo(LocalDate.of(2019, 12, 24))
////                .withRentalBranch("C")
////                .withReturnBranch("D")
//                .withAmount(100L);
//
//        BookingDto bookingDto2 = BookingDto
//                .bookingDto()
//                .withId(1L)
//                .withDateOfBooking(LocalDate.now())
//                .withClient(customer)
//                .withCar(car)
//                .withDateFrom(LocalDate.of(2019, 12, 20))
//                .withDateTo(LocalDate.of(2019, 12, 25))
////                .withRentalBranch("C")
////                .withReturnBranch("D")
//                .withAmount(100L);
//
//        boolean comparisonResult = bookingDto1.equals(bookingDto2);
//
//        Assertions.assertFalse(comparisonResult);
//    }

//    @Test
//    public void givenTwoBookingWithDifferentRentalBranch_whenCompared_resultIsFalse() {
//        CustomerDto customer = getCustomerDto();
//
//        BookingDto bookingDto1 = BookingDto
//                .bookingDto()
//                .withId(1L)
//                .withDateOfBooking(LocalDate.now())
//                .withClient(customer)
////                .withCar("B")
//                .withDateFrom(LocalDate.of(2019, 12, 20))
//                .withDateTo(LocalDate.of(2019, 12, 25))
////                .withRentalBranch("D")
////                .withReturnBranch("D")
//                .withAmount(100L);
//
//        BookingDto bookingDto2 = BookingDto
//                .bookingDto()
//                .withId(1L)
//                .withDateOfBooking(LocalDate.now())
//                .withClient(customer)
////                .withCar("B")
//                .withDateFrom(LocalDate.of(2019, 12, 20))
//                .withDateTo(LocalDate.of(2019, 12, 25))
////                .withRentalBranch("C")
////                .withReturnBranch("D")
//                .withAmount(100L);
//
//        boolean comparisonResult = bookingDto1.equals(bookingDto2);
//
//        Assertions.assertFalse(comparisonResult);
//    }
//
//    @Test
//    public void givenTwoBookingWithDifferentReturnBranch_whenCompared_resultIsFalse() {
//        CustomerDto customer = getCustomerDto();
//
//        BookingDto bookingDto1 = BookingDto
//                .bookingDto()
//                .withId(1L)
//                .withDateOfBooking(LocalDate.now())
//                .withClient(customer)
////                .withCar("B")
//                .withDateFrom(LocalDate.of(2019, 12, 20))
//                .withDateTo(LocalDate.of(2019, 12, 25))
////                .withRentalBranch("C")
////                .withReturnBranch("C")
//                .withAmount(100L);
//
//        BookingDto bookingDto2 = BookingDto
//                .bookingDto()
//                .withId(1L)
//                .withDateOfBooking(LocalDate.now())
//                .withClient(customer)
////                .withCar("B")
//                .withDateFrom(LocalDate.of(2019, 12, 20))
//                .withDateTo(LocalDate.of(2019, 12, 25))
////                .withRentalBranch("C")
////                .withReturnBranch("D")
//                .withAmount(100L);
//
//        boolean comparisonResult = bookingDto1.equals(bookingDto2);
//
//        Assertions.assertFalse(comparisonResult);
//    }

    @Test
    public void givenTwoBookingWithDifferentAmount_whenCompared_resultIsFalse() {
        CustomerDto customer = getCustomerDto();
        CarDto car = getCarDto();

        BookingDto bookingDto1 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(100D);

        BookingDto bookingDto2 = BookingDto
                .bookingDto()
                .withId(1L)
                .withDateOfBooking(LocalDate.now())
                .withClient(customer)
                .withCar(car)
                .withDateFrom(getRental())
//                .withDateTo(LocalDate.of(2019, 12, 25))
//                .withRentalBranch("C")
//                .withReturnBranch("D")
                .withAmount(1000D);

        boolean comparisonResult = bookingDto1.equals(bookingDto2);

        Assertions.assertFalse(comparisonResult);
    }
    private CustomerDto getCustomerDto() {
        return CustomerDto.customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");
    }

    private CustomerDto getCustomerDto2() {
        return CustomerDto.customerDto()
                .withId(2L)
                .withFirstName("X")
                .withLastName("Y")
                .withEmail("Z")
                .withAddress("Q");
    }

    private CarDto getCarDto() {
        return CarDto.carDto()
                .withId(1L)
                .withMake("A")
                .withModel("B")
                .withBodyType("C")
                .withYearOfProduction(1988)
                .withColor("D")
                .withMileage(2019L)
                .withStatus(Status.AVAILABLE)
                .withAmount(5D);
    }

    private CarDto getCarDto2() {
        return CarDto.carDto()
                .withId(2L)
                .withMake("X")
                .withModel("Y")
                .withBodyType("Z")
                .withYearOfProduction(2019)
                .withColor("Q")
                .withMileage(1988L)
                .withStatus(Status.UNAVAILABLE)
                .withAmount(7.5);
    }

    private RentalDto getRental() {
        return RentalDto.rentalDto()
                .withId(1L)
                .withRentalDate(LocalDate.of(2019, 8, 24))
                .withComments("A");
    }

    private RentalDto getRental2() {
        return RentalDto.rentalDto()
                .withId(2L)
                .withRentalDate(LocalDate.of(2019, 8, 30))
                .withComments("B");
    }
}
