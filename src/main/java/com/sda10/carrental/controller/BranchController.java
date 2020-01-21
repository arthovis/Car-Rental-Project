package com.sda10.carrental.controller;

import com.sda10.carrental.dto.*;
import com.sda10.carrental.model.Branch;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CarMapper carMapper;

    @PostMapping(value = "/branch")
    public BranchDto createRental(@RequestBody BranchDto branchDetails) {

        Branch branch = branchMapper.toEntity(branchDetails);

        branch = branchService.createBranch(branch);

        return branchMapper.toDto(branch);
    }

    @GetMapping(value = "/branch/{id}")
    public BranchDto findBranchById(@PathVariable Long id) {

        Branch branchById = branchService.findBranchById(id);

        return branchMapper.toDto(branchById);

    }

    @GetMapping(value = "/branch")
    public List<BranchDto> findAllBranches() {

        List<Branch> branches = branchService.getAllBranches();
        return branches
                .stream()
                .map(branchMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/branch/{id}")
    public ResponseEntity updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) {

        Branch branch = branchMapper.toEntity(branchDto);

        branchService.updateBranch(id, branch);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/branch/{id}")
    public ResponseEntity deleteBranch(@PathVariable Long id) {

        branchService.deleteBranch(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/branch/{id}/employees")
    public ResponseEntity<BranchDto> addEmployeeToBranch(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Branch updatedBranch = branchService.updateBranchWithEmployee(id, employee);
        BranchDto response = branchMapper.toDto(updatedBranch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/branch/{id}/details")
    public ResponseEntity<BranchDto> deleteEmployeeFromBranch(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Branch updatedBranch = branchService.updateBranchWithoutEmployee(id, employee);
        BranchDto response = branchMapper.toDto(updatedBranch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/branch/{id}/cars")
    public ResponseEntity<BranchDto> addCarToBranch(@PathVariable Long id, @RequestBody CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        Branch updatedBranch = branchService.updateBranchWithCar(id, car);
        BranchDto response = branchMapper.toDto(updatedBranch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/branch/{id}/details")
    public ResponseEntity<BranchDto> deleteCarFromBranch(@PathVariable Long id, @RequestBody CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        Branch updatedBranch = branchService.updateBranchWithoutCar(id, car);
        BranchDto response = branchMapper.toDto(updatedBranch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
