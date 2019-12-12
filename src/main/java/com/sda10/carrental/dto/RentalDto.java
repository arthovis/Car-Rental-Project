package com.sda10.carrental.dto;

import java.util.Date;
import java.util.Objects;

public class RentalDto {
    // TODO: 09-Dec-19 add employee and booking entries

    public Long id;
    public Date rentalDate;
    public String comments;

    private RentalDto() {
    }

    public static RentalDto rentalDto() {
        return new RentalDto();
    }

    public RentalDto withId(Long id) {
        this.id = id;
        return this;
    }

    public RentalDto withRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
        return this;
    }

    public RentalDto withComments(String comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDto rentalDto = (RentalDto) o;
        return Objects.equals(id, rentalDto.id) &&
                Objects.equals(rentalDate, rentalDto.rentalDate) &&
                Objects.equals(comments, rentalDto.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalDate, comments);
    }
}
