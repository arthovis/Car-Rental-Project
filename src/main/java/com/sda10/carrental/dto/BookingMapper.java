package com.sda10.carrental.dto;

import com.sda10.carrental.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    RentalMapper rentalMapper;

    @Autowired
    CarReturnMapper carReturnMapper;

    @Autowired
    BranchMapper branchMapper;


    public Booking toEntity(BookingDto dto) {

        Booking entity = new Booking();
        entity.setDateOfBooking(dto.dateOfBooking);
        entity.setDateFrom(dto.dateFrom);
        entity.setDateTo(dto.dateTo);
        entity.setClient(customerMapper.toEntity(dto.client));
        entity.setCar(carMapper.toEntity(dto.car));
        entity.setRentalBranch(dto.rentalBranchDto == null ? null : branchMapper.toEntity(dto.rentalBranchDto));
        entity.setReturnBranch(dto.returnBranchDto == null ? null : branchMapper.toEntity(dto.returnBranchDto));
        entity.setRental(dto.rentalDto == null ? null : rentalMapper.toEntity(dto.rentalDto));
        entity.setCarReturn(dto.carReturnDto == null ? null : carReturnMapper.toEntity(dto.carReturnDto));
        entity.setAmount(dto.amount);
        entity.setBookingStatus(dto.bookingStatus);
        return entity;
    }

    public BookingDto toDto(Booking entity) {
        return BookingDto.bookingDto()
                .withId(entity.getId())
                .withDateOfBooking(entity.getDateOfBooking())
                .withDateFrom(entity.getDateFrom())
                .withDateTo(entity.getDateTo())
                .withClientDto(customerMapper.toDto(entity.getClient()))
                .withCarDto(carMapper.toDto(entity.getCar()))
                .withRentalBranchDto(entity.getRentalBranch() == null ? null : branchMapper.toLightDto(entity.getRentalBranch()))
                .withReturnBranchDto(entity.getReturnBranch() == null ? null : branchMapper.toLightDto(entity.getReturnBranch()))
                .withRentalDto(entity.getRental() == null ? null : rentalMapper.toDto(entity.getRental()))
                .withCarReturnDto(entity.getCarReturn() == null ? null : carReturnMapper.toDto(entity.getCarReturn()))
                .withAmount(entity.getAmount())
                .withStatus(entity.getBookingStatus());
    }
}
