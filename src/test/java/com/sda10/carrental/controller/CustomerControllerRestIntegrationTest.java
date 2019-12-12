package com.sda10.carrental.controller;

import com.sda10.carrental.RestIntegrationTest;
import com.sda10.carrental.dto.CustomerDto;
import com.sda10.carrental.model.Customer;
import com.sda10.carrental.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomerControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Autowired
    public CustomerRepository customerRepository;

    @Test
    public void givenCustomerDetails_whenPostRequestReceived_thenCreateCustomer() {
        CustomerDto customerDetails = CustomerDto.customerDto();

        customerDetails.withFirstName("A")
                .withLastName("B")
                .withEmail("C")
                .withAddress("D");

        String relativePath = "/customers";

        ResponseEntity<CustomerDto> actualResponse = this.testRestTemplate
                .postForEntity(url(relativePath), customerDetails, CustomerDto.class);

        Long newId = actualResponse.getBody().id;

        CustomerDto expectedResponse = customerDetails.withId(newId);

        Customer expectedCustomer = customerRepository.getOne(newId);

        Assertions.assertNotNull(expectedCustomer);
        Assertions.assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void getByIdTest() {
        Customer customer = new Customer();

        customer.setFirstName("A");
        customer.setLastName("B");
        customer.setEmail("C");
        customer.setAddress("D");

        customer = customerRepository.save(customer);

        String relativePath = "/customers/" + customer.getId();

        ResponseEntity<CustomerDto> actualResponse = this.testRestTemplate
                .getForEntity(url(relativePath), CustomerDto.class);

        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }
}
