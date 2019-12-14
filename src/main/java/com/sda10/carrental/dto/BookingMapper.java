package com.sda10.carrental.dto;

import com.sda10.carrental.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toEntity(BookingDto dto) {

        Booking entity = new Booking();
        entity.setDateOfBooking(dto.dateOfBooking);
//        entity.setClient(dto.client);
//        entity.setCar(dto.withCar());
        entity.setDateFrom(dto.dateFrom);
        entity.setDateTo(dto.dateTo);
//        entity.setRentalBranch(dto.withRentalBranch());
//        entity.setReturnBranch(dto.withReturnBranch());
        entity.setAmount(dto.amount);

        return entity;
    }
}
