package com.sda10.carrental.dto;

import com.sda10.carrental.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BranchMapper {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public BranchDto toDto(Branch branch) {
        return BranchDto.branchDto()
                .withId(branch.getId())
                .withAddress(branch.getAddress())
                .withCar(branch.getAvailableCarsList() == null ? null : branch.getAvailableCarsList()
                        .stream()
                        .map(carMapper::toDto)
                        .collect(Collectors.toList()))
                .withEmployees(branch.getEmployeeList() == null ? null : branch.getEmployeeList()
                        .stream()
                        .map(employeeMapper::toDto)
                        .collect(Collectors.toList()));
    }

    public BranchDto toLightDto(Branch branch) {
        return BranchDto.branchDto()
                .withId(branch.getId())
                .withAddress(branch.getAddress());
    }

    public Branch toEntity(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setId(branchDto.id);
        branch.setAddress(branchDto.address);
        branch.setEmployeeList(branchDto.employeeList == null ? null : branchDto.employeeList
                .stream()
                .map(employeeMapper::toEntity)
                .collect(Collectors.toList()));
        branch.setAvailableCarsList(branchDto.availableCarsList == null ? null : branchDto.availableCarsList
                .stream()
                .map(carMapper::toEntity)
                .collect(Collectors.toList()));

        return branch;
    }

}
