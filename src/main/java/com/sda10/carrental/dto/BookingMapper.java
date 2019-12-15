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

    public Booking toEntity(BookingDto dto) {

        Booking entity = new Booking();
        entity.setId(dto.id);
        entity.setDateOfBooking(dto.dateOfBooking);
        entity.setClient(customerMapper.toEntity(dto.client));
        entity.setCar(carMapper.toEntity(dto.car));
        entity.setDateFrom(rentalMapper.toEntity(dto.dateFrom));
//        entity.setDateTo(dto.dateTo);
//        entity.setRentalBranch(dto));
//        entity.setReturnBranch(dto.withReturnBranch());
        entity.setAmount(dto.amount);

        return entity;
    }

    public BookingDto toDto(Booking entity) {
        return BookingDto.bookingDto()
                .withId(entity.getId())
                .withDateOfBooking(entity.getDateOfBooking())
                .withClient(customerMapper.toDto(entity.getClient()))
                .withCar(carMapper.toDto(entity.getCar()))
                .withDateFrom(rentalMapper.toDto(entity.getDateFrom()))
//                .withDateTo(entity.getDateTo())
//                .withRentalBranch(rentalMapper.toDto(entity.getRentalBranch())) TO CHECK
//                .withReturnBranch(returnMapper.toDto(entity.getRentalBranch())) TO CHECK
                .withAmount(entity.getAmount());
    }
}
