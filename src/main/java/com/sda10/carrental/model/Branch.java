package com.sda10.carrental.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    List<Employee> employeeList;

    List<Car> availableCarsList;
}
