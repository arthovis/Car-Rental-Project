package com.sda10.carrental.dto;

import java.util.Objects;

public class RevenueDto {

    public double totalRevenue;

    public RevenueDto() {
    }

    public static RevenueDto revenueDto() {
        return new RevenueDto();
    }

    public RevenueDto withTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RevenueDto)) return false;
        RevenueDto that = (RevenueDto) o;
        return Double.compare(that.totalRevenue, totalRevenue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRevenue);
    }

    @Override
    public String toString() {
        return "RevenueDto{" +
                "totalRevenue=" + totalRevenue +
                '}';
    }
}
