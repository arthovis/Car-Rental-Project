package com.sda10.carrental.service;

import com.sda10.carrental.model.Customer;
import com.sda10.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);

        if (customerToUpdate.isPresent()) {
            customer.setId(id);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteCustomer(Long id) {
        Customer customerToDelete = customerRepository.findById(id).get();

        customerRepository.delete(customerToDelete);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getAllCustomers(Integer pageIndex, Integer pageSize) {
        Pageable paging = PageRequest.of(pageIndex, pageSize);

        Page<Customer> pagedResult = customerRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
