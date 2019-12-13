package com.sda10.carrental.controller;

import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDetails) {
        Employee employee = new Employee();

        employee.setNameAndSurname(employeeDetails.nameAndSurname);
        employee.setJobPosition(employeeDetails.jobPosition);

        employee = employeeService.createEmployee(employee);

        return EmployeeDto.employeeDto()
                .withId(employee.getId())
                .withNameAndSurname(employee.getNameAndSurname())
                .withJobPosition(employee.getJobPosition());
    }

    @GetMapping(value = "/employees/{id}")
    public EmployeeDto findEmployeeById(@PathVariable Long id) {
        Employee employeeById = employeeService.getEmployeeById(id);

        return EmployeeDto.employeeDto()
                .withId(employeeById.getId())
                .withNameAndSurname(employeeById.getNameAndSurname())
                .withJobPosition(employeeById.getJobPosition());
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDetails) {

        Employee employee = new Employee();

        employee.setNameAndSurname(employeeDetails.nameAndSurname);
        employee.setJobPosition(employeeDetails.jobPosition);

        employeeService.updateEmployee(id, employee);

        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
