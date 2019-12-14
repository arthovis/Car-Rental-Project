package com.sda10.carrental.dto;

import com.sda10.carrental.model.Booking;
import com.sda10.carrental.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    @Autowired
    CustomerMapper customerMapper;

    public Booking toEntity(BookingDto dto) {
        Customer client = new Customer();

        Booking entity = new Booking();
        entity.setDateOfBooking(dto.dateOfBooking);
        entity.setClient(customerMapper.toEntity(dto.client));
//        entity.setCar(dto.car);
        entity.setDateFrom(dto.dateFrom);
        entity.setDateTo(dto.dateTo);
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
//                .withCar(entity.getCar())
                .withDateFrom(entity.getDateFrom())
                .withDateTo(entity.getDateTo())
                .withAmount(entity.getAmount());
    }
}
