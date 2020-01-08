package com.sda10.carrental.dto;

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
}
