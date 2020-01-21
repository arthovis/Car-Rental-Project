package com.sda10.carrental.controller;

import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.dto.EmployeeMapper;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping(value = "/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDetails) {
        Employee employee = employeeMapper.toEntity(employeeDetails);
        employee = employeeService.createEmployee(employee);

        return employeeMapper.toDto(employee);
    }

    @GetMapping(value = "/employees/{id}")
    public EmployeeDto findEmployeeById(@PathVariable Long id) {
        Employee employeeById = employeeService.getEmployeeById(id);

        return employeeMapper.toDto(employeeById);
    }

    @GetMapping(value = "/employees")
    public List<EmployeeDto> findAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();
        return employees
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDetails) {

        Employee employee = employeeMapper.toEntity(employeeDetails);
        employeeService.updateEmployee(id, employee);

        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
