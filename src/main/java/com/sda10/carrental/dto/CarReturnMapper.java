package com.sda10.carrental.dto;

import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class CarReturnMapper {

    public CarReturn toEntity(CarReturnDto carReturnDtoDetails) {
        CarReturn carReturn = new CarReturn();
        carReturn.setEmployee(fakeEmployeeMapper(carReturnDtoDetails.employeeDto));
        carReturn.setDateOfReturn(carReturnDtoDetails.dateOfReturn);
        carReturn.setAdditionalPayment(carReturnDtoDetails.additionalPayment);
        carReturn.setComments(carReturnDtoDetails.comments);
        return carReturn;
    }

    public Employee fakeEmployeeMapper(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.id);
        employee.setJobPosition(employeeDto.jobPosition);
        employee.setNameAndSurname(employeeDto.nameAndSurname);
        return employee;
    }

    public CarReturnDto toDto(CarReturn carReturn) {
        return CarReturnDto.carReturnDto()
                .withId(carReturn.getId())
                .withEmployeeDto(fakeEmployeeDtoMapper(carReturn.getEmployee()))
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());
    }

    public EmployeeDto fakeEmployeeDtoMapper(Employee employee) {
        return EmployeeDto.employeeDto()
                .withId(employee.getId())
                .withNameAndSurname(employee.getNameAndSurname())
                .withJobPosition(employee.getJobPosition());
    }
}
