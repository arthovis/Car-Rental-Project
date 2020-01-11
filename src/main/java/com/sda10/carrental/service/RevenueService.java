package com.sda10.carrental.service;

import com.sda10.carrental.model.Booking;
import com.sda10.carrental.model.Revenue;
import com.sda10.carrental.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {

    @Autowired
    public RevenueRepository revenueRepository;

    public Revenue computeRevenueForBranchWithId(Long branchID) {
        List<Booking> list = revenueRepository.bookingsByBranchIdAndStatus(branchID);
        double totalRevenue = list
                .stream()
                .map(booking -> booking.getAmount() + booking.getCarReturn().getAdditionalPayment())
                .reduce((double) 0, Double::sum);

        Revenue revenue = new Revenue();
        revenue.setTotalRevenue(totalRevenue);

        return revenue;
    }
}