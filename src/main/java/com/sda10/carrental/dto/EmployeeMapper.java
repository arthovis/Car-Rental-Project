package com.sda10.carrental.dto;

import com.sda10.carrental.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    @Autowired
    BranchMapper branchMapper;

    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.id);
        employee.setNameAndSurname(dto.nameAndSurname);
        employee.setJobPosition(dto.jobPosition);
        employee.setBranch(dto.branchDto == null ? null : branchMapper.toEntity(dto.branchDto));

        return employee;
    }

    public EmployeeDto toDto(Employee entity) {
        return EmployeeDto.employeeDto()
                .withId(entity.getId())
                .withNameAndSurname(entity.getNameAndSurname())
                .withJobPosition(entity.getJobPosition())
                .withBranchDto(entity.getBranch() == null ? null : branchMapper.toLightDto(entity.getBranch()));
    }

}
