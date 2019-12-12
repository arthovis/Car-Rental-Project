package com.sda10.carrental.dto;

import java.util.Objects;

public class CustomerDto {

    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String address;

    private CustomerDto() {
    }

    public static CustomerDto customerDto() {
        return new CustomerDto();
    }

    public CustomerDto withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerDto withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDto withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerDto withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDto withAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, address);
    }
}
