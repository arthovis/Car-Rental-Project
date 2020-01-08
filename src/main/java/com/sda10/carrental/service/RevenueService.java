package com.sda10.carrental.service;

import com.sda10.carrental.model.Booking;
import com.sda10.carrental.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {

    @Autowired
    public RevenueRepository revenueRepository;

    public double bookingFilter(Long branchID) {
        List<Booking> list = revenueRepository.bookingsByBranchIdAndStatus(branchID);
        return list.stream().map(booking -> booking.getAmount()).count();
    }
}
