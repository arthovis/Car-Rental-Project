package com.sda10.carrental.dto;

import com.sda10.carrental.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RentalMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private BranchMapper branchMapper;


    public Rental toEntity(RentalDto dto) {
        Rental rental = new Rental();
        rental.setId(dto.id);
        rental.setRentalDate(dto.rentalDate);
        rental.setComments(dto.comments);
        rental.setEmployee(employeeMapper.toEntity(dto.employeeDto));
        rental.setBranch(branchMapper.toEntity(dto.branchDto));
        return rental;
    }

    public RentalDto toDto(Rental entity) {
        return RentalDto.rentalDto()
                .withId(entity.getId())
                .withRentalDate(entity.getRentalDate())
                .withComments(entity.getComments())
                .withEmployeeDto(employeeMapper.toDto(entity.getEmployee()))
                .withBranchDto(branchMapper.toLightDto(entity.getBranch()));
    }
}