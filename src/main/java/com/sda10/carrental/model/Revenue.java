package com.sda10.carrental.model;

import org.springframework.stereotype.Component;

@Component
public class Revenue {

    private double totalRevenue;

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
