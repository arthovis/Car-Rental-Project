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

    public Booking toEntity(BookingDto dto) {

        Booking entity = new Booking();
        entity.setDateOfBooking(dto.dateOfBooking);
        entity.setClient(customerMapper.toEntity(dto.client));
        entity.setCar(carMapper.toEntity(dto.car));
        entity.setDateFrom(rentalMapper.toEntity(dto.dateFrom));
//        entity.setRentalBranch(dto));
//        entity.setReturnBranch(dto.withReturnBranch());
        entity.setAmount(dto.amount);
        entity.setCarReturn(carReturnMapper.toLightEntity(dto.carReturnDto));
        entity.setBookingStatus(dto.bookingStatus);
        return entity;
    }

    public BookingDto toDto(Booking entity) {
        return BookingDto.bookingDto()
                .withId(entity.getId())
                .withDateOfBooking(entity.getDateOfBooking())
                .withClient(customerMapper.toDto(entity.getClient()))
                .withCar(carMapper.toDto(entity.getCar()))
                .withDateFrom(rentalMapper.toDto(entity.getDateFrom()))
                .withAmount(entity.getAmount())
                .withCarReturnDto(carReturnMapper.toLightDto(entity.getCarReturn()))
                .withStatus(entity.getBookingStatus());
    }
}
