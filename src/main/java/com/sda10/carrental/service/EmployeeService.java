package com.sda10.carrental.service;

import com.sda10.carrental.model.Employee;
import com.sda10.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void deleteEmployee(Long  id){
        Employee existingEmployee=employeeRepository.findById(id).get();

        employeeRepository.delete(existingEmployee);
    }

}