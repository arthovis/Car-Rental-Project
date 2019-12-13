package com.sda10.carrental.dto;

import com.sda10.carrental.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualCustomers_whenCompared_resultIsTrue() {
        CustomerDto customerDto1 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        CustomerDto customerDto2 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        boolean comparisonResult = customerDto1.equals(customerDto2);

        Assertions.assertTrue(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCustomerFirstNames_whenCompared_resultIsFalse() {
        CustomerDto customerDto1 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("B")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        CustomerDto customerDto2 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        boolean comparisonResult = customerDto1.equals(customerDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCustomerLastNames_whenCompared_resultIsFalse() {
        CustomerDto customerDto1 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("A")
                .withEmail("C")
                .withAddress("D");

        CustomerDto customerDto2 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        boolean comparisonResult = customerDto1.equals(customerDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCustomerEmails_whenCompared_resultIsFalse() {
        CustomerDto customerDto1 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("B")
                .withAddress("D");

        CustomerDto customerDto2 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        boolean comparisonResult = customerDto1.equals(customerDto2);

        Assertions.assertFalse(comparisonResult);
    }

    @Test
    public void givenTwoDifferentCustomerAddresses_whenCompared_resultIsFalse() {
        CustomerDto customerDto1 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("C");

        CustomerDto customerDto2 = CustomerDto
                .customerDto()
                .withId(1L)
                .withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        boolean comparisonResult = customerDto1.equals(customerDto2);

        Assertions.assertFalse(comparisonResult);
    }
}
