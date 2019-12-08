package com.sda10.carrental.repository;

import com.sda10.carrental.model.CarRentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalOfficeRepository extends JpaRepository<CarRentalOffice, Long> {
}
