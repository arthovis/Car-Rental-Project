package com.sda10.carrental.controller;

import com.sda10.carrental.dto.BranchDto;
import com.sda10.carrental.dto.BranchMapper;
import com.sda10.carrental.dto.CarDto;
import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.model.Branch;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;

    @PostMapping(value = "/branch")
    public BranchDto createRental(@RequestBody BranchDto branchDetails) {

        Branch branch = new Branch();
        List<Employee> employees = branchMapper.employeeDtoToEntity(branchDetails);

        List<Car> cars = branchMapper.carDtoToEntity(branchDetails);


        branch.setAddress(branchDetails.address);
        branch.setEmployeeList(employees);
        branch.setAvailableCarsList(cars);

        branch = branchService.createBranch(branch);

        List<EmployeeDto> employeeDtos = branchMapper.employeeToDto(branchDetails);
        List<CarDto> carDtos = branchMapper.carToDto(branchDetails);

        return BranchDto.branchDto()
                .withId(branch.getId())
                .withAddress(branch.getAddress())
                .withEmployees(employeeDtos)
                .withCar(carDtos);
    }

    @GetMapping(value = "/branch/{id}")
    public BranchDto findBranchById(@PathVariable Long id) {
        BranchDto branchDto = BranchDto.branchDto();

        Branch branchById = branchService.findBranchById(id);

        List<EmployeeDto> employeeDtos = branchMapper.employeeToDto(branchDto);
        List<CarDto> carDtos = branchMapper.carToDto(branchDto);
        return BranchDto.branchDto()
                .withId(branchById.getId())
                .withAddress(branchById.getAddress())
                .withEmployees(employeeDtos)
                .withCar(carDtos);
    }

    @PutMapping(value = "/branch/{id}")
    public ResponseEntity updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) {

        Branch branch = new Branch();

        List<Car> availableCars = branchMapper.carDtoToEntity(branchDto);

        List<Employee> employees = branchMapper.employeeDtoToEntity(branchDto);

        branch.setAddress(branchDto.address);
        branch.setEmployeeList(employees);
        branch.setAvailableCarsList(availableCars);

        branchService.updateBranch(id, branch);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/branch/{id}")
    public ResponseEntity deleteBranch(@PathVariable Long id) {

        branchService.deleteBranch(id);

        return new ResponseEntity(HttpStatus.OK);
    }


}
