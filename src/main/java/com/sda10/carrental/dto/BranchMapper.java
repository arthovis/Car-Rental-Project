package com.sda10.carrental.dto;

import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchMapper {


    public List<Employee> employeeDtoToEntity(BranchDto branchDetails) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDto employeeDto : branchDetails.employeeList) {
            Employee employee = new Employee();
            employee.setId(employeeDto.id);
            employee.setJobPosition(employeeDto.jobPosition);
            employee.setNameAndSurname(employeeDto.nameAndSurname);
            employees.add(employee);
        }
        return employees;
    }

    public List<EmployeeDto> employeeToDto(BranchDto branchDetails) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (EmployeeDto employeeDto : branchDetails.employeeList) {
            Employee employee = new Employee();
            employeeDto.withId(employee.getId());
            employeeDto.withJobPosition(employee.getJobPosition());
            employeeDto.withNameAndSurname(employee.getNameAndSurname());
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    public List<Car> carDtoToEntity(BranchDto branchDetails) {
        List<Car> cars = new ArrayList<>();
        for (CarDto carDto : branchDetails.availableCarsList) {
            Car car = new Car();
            car.setId(carDto.id);
            car.setMake(carDto.make);
            car.setModel(carDto.model);
            car.setBodyType(carDto.bodyType);
            car.setYearOfProduction(carDto.yearOfProduction);
            car.setColor(carDto.color);
            car.setMileage(carDto.mileage);
            car.setStatus(carDto.status);
            car.setAmount(carDto.amount);
            cars.add(car);
        }
        return cars;
    }

    public List<CarDto> carToDto(BranchDto branchDetails) {
        List<CarDto> carDtos = new ArrayList<>();
        for (CarDto carDto : branchDetails.availableCarsList) {
            Car car = new Car();
            carDto.withId(car.getId())
                    .withMake(car.getMake())
                    .withModel(car.getModel())
                    .withBodyType(car.getBodyType())
                    .withYearOfProduction(car.getYearOfProduction())
                    .withColor(car.getColor())
                    .withMileage(car.getMileage())
                    .withStatus(car.getStatus())
                    .withAmount(car.getAmount());

            carDtos.add(carDto);
        }
        return carDtos;
    }

}
