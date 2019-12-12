package com.sda10.carrental.repository;

import com.sda10.carrental.model.CarReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarReturnRepository extends JpaRepository<CarReturn, Long> {
}
