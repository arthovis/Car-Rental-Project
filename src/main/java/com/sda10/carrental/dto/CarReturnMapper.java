package com.sda10.carrental.dto;

import com.sda10.carrental.model.CarReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarReturnMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private BranchMapper branchMapper;

    public CarReturn toEntity(CarReturnDto carReturnDtoDetails) {
        CarReturn carReturn = new CarReturn();
        carReturn.setEmployee(employeeMapper.toEntity(carReturnDtoDetails.employeeDto));
        carReturn.setBranch(branchMapper.toEntity(carReturnDtoDetails.branchDto));
        carReturn.setDateOfReturn(carReturnDtoDetails.dateOfReturn);
        carReturn.setAdditionalPayment(carReturnDtoDetails.additionalPayment);
        carReturn.setComments(carReturnDtoDetails.comments);
        return carReturn;
    }

    public CarReturn toLightEntity(CarReturnDto carReturnDtoDetails) {
        CarReturn carReturn = new CarReturn();
        carReturn.setDateOfReturn(carReturnDtoDetails.dateOfReturn);
        carReturn.setAdditionalPayment(carReturnDtoDetails.additionalPayment);
        carReturn.setComments(carReturnDtoDetails.comments);
        return carReturn;
    }

    public CarReturnDto toDto(CarReturn carReturn) {
        return CarReturnDto.carReturnDto()
                .withId(carReturn.getId())
                .withEmployeeDto(employeeMapper.toDto(carReturn.getEmployee()))
                .withBranchDto(branchMapper.toLightDto(carReturn.getBranch()))
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());
    }

    public CarReturnDto toLightDto(CarReturn carReturn) {
        return CarReturnDto.carReturnDto()
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());
    }

}
