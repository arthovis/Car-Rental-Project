package com.sda10.carrental.dto;

import com.sda10.carrental.model.Rental;
import org.springframework.stereotype.Component;


@Component
public class RentalMapper {
    public Rental toEntity(RentalDto dto) {
        Rental rental = new Rental();
        rental.setId(dto.id);
        rental.setRentalDate(dto.rentalDate);
        rental.setComments(dto.comments);
        return rental;
    }

    public RentalDto toDto(Rental entity) {
        return RentalDto.rentalDto()
                .withId(entity.getId())
                .withRentalDate(entity.getRentalDate())
                .withComments(entity.getComments());
    }
}