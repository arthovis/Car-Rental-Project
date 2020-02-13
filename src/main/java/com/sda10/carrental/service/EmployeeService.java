package com.sda10.carrental.service;

import com.sda10.carrental.model.Employee;
import com.sda10.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.getOne(id);
    }

    public Employee updateEmployee(Long  id, Employee employee){
        Optional<Employee> employeeToUpdate=employeeRepository.findById(id);
        if(employeeToUpdate.isPresent()){
            employee.setId(id);
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id).get();

        employeeRepository.delete(existingEmployee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllEmployees(Integer pageIndex, Integer pageSize) {
        Pageable paging = PageRequest.of(pageIndex, pageSize);

        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
