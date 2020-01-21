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

    public CarReturn toEntity(CarReturnDto carReturnDto) {
        CarReturn carReturn = new CarReturn();
        carReturn.setId(carReturnDto.id);
        carReturn.setEmployee(carReturnDto.employeeDto == null ? null : employeeMapper.toEntity(carReturnDto.employeeDto));
        carReturn.setBranch(carReturnDto.branchDto == null ? null : branchMapper.toEntity(carReturnDto.branchDto));
        carReturn.setDateOfReturn(carReturnDto.dateOfReturn);
        carReturn.setAdditionalPayment(carReturnDto.additionalPayment);
        carReturn.setComments(carReturnDto.comments);
        return carReturn;
    }

    public CarReturnDto toDto(CarReturn carReturn) {
        return CarReturnDto.carReturnDto()
                .withId(carReturn.getId())
                .withEmployeeDto(carReturn.getEmployee() == null ? null : employeeMapper.toDto(carReturn.getEmployee()))
                .withBranchDto(carReturn.getBranch() == null ? null : branchMapper.toLightDto(carReturn.getBranch()))
                .withDateOfReturn(carReturn.getDateOfReturn())
                .withAdditionalPayment(carReturn.getAdditionalPayment())
                .withComments(carReturn.getComments());
    }

}
