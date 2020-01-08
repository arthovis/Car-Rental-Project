package com.sda10.carrental.repository;

import com.sda10.carrental.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN b.dateFrom r JOIN r.branch br WHERE" +
            " br.id = :branchID AND (b.bookingStatus = 'COMPLETED' OR b.bookingStatus = 'CANCELLED')")
    List<Booking> bookingsByBranchIdAndStatus(@Param("branchID") Long branchID);

}
