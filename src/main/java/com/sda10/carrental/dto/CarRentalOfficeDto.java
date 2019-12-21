package com.sda10.carrental.dto;

import java.util.List;
import java.util.Objects;

public class CarRentalOfficeDto {

    public Long id;
    public String name;
    public String internetDomain;
    public String contactAddress;
    public String owner;
    public String logoType;
    public List<BranchDto> branches;


    private CarRentalOfficeDto() {
    }

    public static CarRentalOfficeDto carRentalOfficeDto() {
        return new CarRentalOfficeDto();
    }

    public CarRentalOfficeDto withId(Long id) {
        this.id = id;
        return this;
    }

    public CarRentalOfficeDto withName(String name) {
        this.name = name;
        return this;
    }

    public CarRentalOfficeDto withInternetDomain(String internetDomain) {
        this.internetDomain = internetDomain;
        return this;
    }

    public CarRentalOfficeDto withContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public CarRentalOfficeDto withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public CarRentalOfficeDto withLogoType(String logoType) {
        this.logoType = logoType;
        return this;
    }

    public CarRentalOfficeDto withBranches(List<BranchDto> branches) {
        this.branches = branches;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRentalOfficeDto that = (CarRentalOfficeDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(internetDomain, that.internetDomain) &&
                Objects.equals(contactAddress, that.contactAddress) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(logoType, that.logoType) &&
                Objects.equals(branches, that.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, internetDomain, contactAddress, owner, logoType, branches);
    }
}
