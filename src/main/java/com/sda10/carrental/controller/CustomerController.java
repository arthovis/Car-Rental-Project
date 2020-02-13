package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CustomerDto;
import com.sda10.carrental.dto.CustomerMapper;
import com.sda10.carrental.model.Customer;
import com.sda10.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    public CustomerMapper customerMapper;

    @Autowired
    public CustomerService customerService;

    @PostMapping(value = "/customers")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDetails) {
        Customer customer = customerMapper.toEntity(customerDetails);

        customer = customerService.createCustomer(customer);

        return customerMapper.toDto(customer);
    }

    @GetMapping(value = "/customers/{id}")
    public CustomerDto findById(@PathVariable Long id) {

        Customer customerById = customerService.findCustomerById(id);

        return customerMapper.toDto(customerById);
    }

    @GetMapping(value = "/customers-list")
    public List<CustomerDto> findAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();
        return customers
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerDto>> findAllCustomers(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "5") Integer pageSize) {

        List<Customer> customers = customerService.getAllCustomers(pageIndex, pageSize);
        List<CustomerDto> response = customers
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDetails) {

        try {
            Customer customer = customerMapper.toEntity(customerDetails);
            customerService.updateCustomer(id, customer);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Customer failed to update");
        }
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Customer to delete was not found");
        }
    }
}
