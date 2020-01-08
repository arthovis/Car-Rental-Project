package com.sda10.carrental.controller;

import com.sda10.carrental.dto.RevenueDto;
import com.sda10.carrental.model.Revenue;
import com.sda10.carrental.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevenueController {

    @Autowired
    RevenueService revenueService;

    @GetMapping(value = "/revenues")
    public RevenueDto revenueByBranch(@RequestParam Long branchID) {
        Revenue revenue = revenueService.computeRevenueForBranchWithId(branchID);
        return RevenueDto.revenueDto().withTotalRevenue(revenue.getTotalRevenue());
    }
}
