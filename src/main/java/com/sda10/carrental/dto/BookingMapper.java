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
    CarReturnMapper carReturnMapper;

    public Booking toEntity(BookingDto dto) {
//        Customer client = new Customer();
//        Car car = new Car();
// Nu este nevoie ca creezi client si car, poti sa le stergi

        Booking entity = new Booking();
        entity.setDateOfBooking(dto.dateOfBooking);
        entity.setClient(customerMapper.toEntity(dto.client));
        entity.setCar(carMapper.toEntity(dto.car));
        entity.setDateFrom(dto.dateFrom);
        entity.setDateTo(dto.dateTo);
//        entity.setRentalBranch(dto));
//        entity.setReturnBranch(dto.withReturnBranch());
        entity.setAmount(dto.amount);
        entity.setCarReturn(carReturnMapper.toLightEntity(dto.carReturnDto));
        return entity;
    }

    public BookingDto toDto(Booking entity) {
        return BookingDto.bookingDto()
                .withId(entity.getId())
                .withDateOfBooking(entity.getDateOfBooking())
                .withClient(customerMapper.toDto(entity.getClient()))
                .withCar(carMapper.toDto(entity.getCar()))
                .withDateFrom(entity.getDateFrom())
                .withDateTo(entity.getDateTo())
                .withAmount(entity.getAmount())
                .withCarReturnDto(carReturnMapper.toLightDto(entity.getCarReturn()));
    }
}
