package com.sda10.carrental.dto;

import com.sda10.carrental.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerDto dto) {
        Customer entity = new Customer();
        entity.setId(dto.id);
        entity.setFirstName(dto.firstName);
        entity.setLastName(dto.lastName);
        entity.setEmail(dto.email);
        entity.setAddress(dto.address);

        return entity;
    }

    public CustomerDto toDto(Customer entity) {
        return CustomerDto.customerDto()
                .withId(entity.getId())
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withEmail(entity.getEmail())
                .withAddress(entity.getAddress());
    }
}
