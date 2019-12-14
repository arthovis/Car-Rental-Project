package com.sda10.carrental.dto;

import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class CarReturnMapper {

    public CarReturn toEntity(CarReturnDto carReturnDtoDetails) {
        CarReturn carReturn = new CarReturn();
        Employee employee = new Employee();

        employee.setId(carReturnDtoDetails.employeeDto.id);
        employee.setJobPosition(carReturnDtoDetails.employeeDto.jobPosition);
        employee.setNameAndSurname(carReturnDtoDetails.employeeDto.nameAndSurname);
        employee.setId(carReturnDtoDetails.employeeDto.id);

        carReturn.setEmployee(employee);
        carReturn.setDateOfReturn(carReturnDtoDetails.dateOfReturn);
        carReturn.setAdditionalPayment(carReturnDtoDetails.additionalPayment);
        carReturn.setComments(carReturnDtoDetails.comments);
        return carReturn;
    }

    public CarReturnDto toDto(CarReturn carReturn) {
        return CarReturnDto.carReturnDto()
                .withId(carReturn.getId())
                .withEmployeeDto(EmployeeDto.employeeDto()
                        .withId(carReturn.getEmployee().getId())
                        .withNameAndSurname(carReturn.getEmployee().getNameAndSurname())
                        .withJobPosition(carReturn.getEmployee().getJobPosition()))
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());
    }
}
