package com.sda10.carrental;

import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.repository.CarReturnRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CarReturnRepository carReturnRepository;

	@Bean
	CommandLineRunner loadData() {
		return args ->{ employeeRepository.save(newEmployee);
		carReturnRepository.save(newCarReturn);
		};
	}

	Employee newEmployee = createEmployee();
	private Employee createEmployee() {
		Employee employee = new Employee();
		employee.setNameAndSurname("popescu ion");
		employee.setJobPosition("manager");
		return employee;
	}

	CarReturn newCarReturn = createCarReturn();
	private CarReturn createCarReturn() {
		CarReturn carReturn = new CarReturn();
		carReturn.setEmployee(this.newEmployee);
		carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
		carReturn.setAdditionalPayment(22.0);
		carReturn.setComments("first car");
		return carReturn;
	}


}

