package com.sda10.carrental.dto;

import com.sda10.carrental.model.CarRentalOffice;
import org.springframework.stereotype.Component;

@Component
public class CarRentalOfficeMapper {

    public CarRentalOffice toEntity(CarRentalOfficeDto dto) {

        CarRentalOffice entity = new CarRentalOffice();

        entity.setId(dto.id);
        entity.setName(dto.name);
        entity.setInternetDomain(dto.internetDomain);
        entity.setContactAddress(dto.contactAddress);
        entity.setOwner(dto.owner);
        entity.setLogoType(dto.logoType);

        return entity;
    }

    public CarRentalOfficeDto toDto(CarRentalOffice entity) {

        return CarRentalOfficeDto.carRentalOfficeDto()
                .withId(entity.getId())
                .withName(entity.getName())
                .withInternetDomain(entity.getInternetDomain())
                .withContactAddress(entity.getContactAddress())
                .withOwner(entity.getOwner())
                .withLogoType(entity.getLogoType());
    }


}
