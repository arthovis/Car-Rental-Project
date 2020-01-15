package com.sda10.carrental.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "car_rental_office")
public class CarRentalOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String name;

    @NotNull
    private String internetDomain;

    @NotNull
    private String contactAddress;

    @NotNull
    private String owner;

    @NotNull
    private String logoType;

    @OneToMany
    @JoinColumn(name = "branch_id")
    List<Branch> branches = new ArrayList<>();

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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public void removeBranch(Branch branch) {
        branches.remove(branch);
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
                Objects.equals(logoType, that.logoType) &&
                Objects.equals(branches, that.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, internetDomain, contactAddress, owner, logoType, branches);
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
                ", branches=" + branches +
                '}';
    }
}
