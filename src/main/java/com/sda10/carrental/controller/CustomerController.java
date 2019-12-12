package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CustomerDto;
import com.sda10.carrental.model.Customer;
import com.sda10.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @PostMapping(value = "/customers")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDetails) {
        Customer customer = new Customer();

        customer.setFirstName(customerDetails.firstName);
        customer.setLastName(customerDetails.lastName);
        customer.setEmail(customerDetails.email);
        customer.setAddress(customerDetails.address);

        customer = customerService.createCustomer(customer);

        return CustomerDto.customerDto()
                .withId(customer.getId())
                .withFirstName(customer.getFirstName())
                .withLastName(customer.getLastName())
                .withEmail(customer.getEmail())
                .withAddress(customer.getAddress());
    }
}
