package com.sda10.carrental.model;

import java.util.Objects;

public class CarRentalOffice {

    private Long id;
    private String name;
    private String internetDomain;
    private String contactAddress;
    private String owner;
    private String logoType;
//    public List<Branch> branches;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternetDomain() {
        return internetDomain;
    }

    public void setInternetDomain(String internetDomain) {
        this.internetDomain = internetDomain;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLogoType() {
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRentalOffice that = (CarRentalOffice) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(internetDomain, that.internetDomain) &&
                Objects.equals(contactAddress, that.contactAddress) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(logoType, that.logoType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, internetDomain, contactAddress, owner, logoType);
    }

    @Override
    public String toString() {
        return "CarRentalOffice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", internetDomain='" + internetDomain + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", owner='" + owner + '\'' +
                ", logoType='" + logoType + '\'' +
                '}';
    }
}
