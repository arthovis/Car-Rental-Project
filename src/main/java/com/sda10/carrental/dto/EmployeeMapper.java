package com.sda10.carrental.dto;

import com.sda10.carrental.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.id);
        employee.setNameAndSurname(dto.nameAndSurname);
        employee.setJobPosition(dto.jobPosition);

        return employee;
    }

    public EmployeeDto toDto(Employee entity) {
        return EmployeeDto.employeeDto()
                .withId(entity.getId())
                .withNameAndSurname(entity.getNameAndSurname())
                .withJobPosition(entity.getJobPosition());
    }
}
