package com.sda10.carrental.dto;

import com.sda10.carrental.model.Branch;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchMapper {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

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

    public BranchDto toDto(Branch branch) {
        return BranchDto.branchDto()
                .withId(branch.getId())
                .withAddress(branch.getAddress())
                .withCar(branch.getAvailableCarsList()
                        .stream()
                        .map(carMapper::toDto)
                        .collect(Collectors.toList()))
                .withEmployees(branch.getEmployeeList()
                        .stream()
                        .map(employeeMapper::toDto)
                        .collect(Collectors.toList()));
    }

    public BranchDto toLightDto(Branch branch) {
        return BranchDto.branchDto()
                .withId(branch.getId())
                .withAddress(branch.getAddress());
    }

    public Branch toEntity(BranchDto branchDto) {
        Branch branch = new Branch();

        branch.setId(branchDto.id);
        branch.setAddress(branchDto.address);
        branch.setEmployeeList(branchDto.employeeList.stream()
                .map(employeeMapper::toEntity)
                .collect(Collectors.toList()));
        branch.setAvailableCarsList(branchDto.availableCarsList.stream()
                .map(carMapper::toEntity)
                .collect(Collectors.toList()));

        return branch;
    }
}
