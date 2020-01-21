package com.sda10.carrental.dto;

import com.sda10.carrental.model.CarRentalOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarRentalOfficeMapper {

    @Autowired
    private BranchMapper branchMapper;

    public CarRentalOffice toEntity(CarRentalOfficeDto dto) {

        CarRentalOffice entity = new CarRentalOffice();
        entity.setId(dto.id);
        entity.setName(dto.name);
        entity.setInternetDomain(dto.internetDomain);
        entity.setContactAddress(dto.contactAddress);
        entity.setOwner(dto.owner);
        entity.setLogoType(dto.logoType);
        entity.setBranches(Optional.ofNullable(dto.branches).orElse(Collections.emptyList())
                .stream()
                .map(branchMapper::toEntity)
                .collect(Collectors.toList()));

        return entity;
    }

    public CarRentalOfficeDto toDto(CarRentalOffice entity) {

        return CarRentalOfficeDto.carRentalOfficeDto()
                .withId(entity.getId())
                .withName(entity.getName())
                .withInternetDomain(entity.getInternetDomain())
                .withContactAddress(entity.getContactAddress())
                .withOwner(entity.getOwner())
                .withLogoType(entity.getLogoType())
                .withBranches(Optional.ofNullable(entity.getBranches()).orElse(Collections.emptyList())
                        .stream()
                        .map(branchMapper::toDto)
                        .collect(Collectors.toList()));
    }

}
