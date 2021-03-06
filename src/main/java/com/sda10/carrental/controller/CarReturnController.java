package com.sda10.carrental.controller;

import com.sda10.carrental.dto.CarReturnDto;
import com.sda10.carrental.dto.CarReturnMapper;
import com.sda10.carrental.dto.EmployeeDto;
import com.sda10.carrental.dto.EmployeeMapper;
import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.service.CarReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarReturnController {

    private CarReturnService carReturnService;
    private CarReturnMapper carReturnMapper;
    private EmployeeMapper employeeMapper;


    @Autowired
    public CarReturnController(CarReturnService carReturnService, CarReturnMapper carReturnMapper, EmployeeMapper employeeMapper) {
        this.carReturnService = carReturnService;
        this.carReturnMapper = carReturnMapper;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/car-return")
    public ResponseEntity<CarReturnDto> createCarReturn(@RequestBody CarReturnDto carReturnDtoDetails) {

        CarReturn newCarReturn = carReturnMapper.toEntity(carReturnDtoDetails);
        CarReturn createdCarReturn = carReturnService.createCarReturn(newCarReturn);

        CarReturnDto response = carReturnMapper.toDto(createdCarReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/car-return")
    public ResponseEntity<List<CarReturnDto>> getAllCarReturns(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "5") Integer pageSize) {

        List<CarReturn> carReturns = carReturnService.findAllCarReturn(pageIndex, pageSize);
        List<CarReturnDto> response = carReturns
                .stream()
                .map(carReturnMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/car-return/{id}")
    public ResponseEntity<CarReturnDto> getCarReturnById(@PathVariable("id") Long id) {

        CarReturn carReturn = carReturnService.findCarReturnById(id);

        CarReturnDto response = carReturnMapper.toDto(carReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/car-return/{id}")
    public ResponseEntity<CarReturnDto> updateCarReturn(@PathVariable("id") Long id, @RequestBody CarReturnDto carReturnDtoDetails) {

        CarReturn carReturnToUpdate = carReturnMapper.toEntity(carReturnDtoDetails);
        CarReturn updatedCarReturn = carReturnService.updateCarReturn(id, carReturnToUpdate);

        CarReturnDto response = carReturnMapper.toDto(updatedCarReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/car-return/{id}")
    public ResponseEntity deleteCarReturn(@PathVariable("id") Long id) {

        carReturnService.deleteCarReturn(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/car-return/{id}/employees")
    public ResponseEntity<CarReturnDto> addEmployeeToCarReturn(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {

        Employee employee = employeeMapper.toEntity(employeeDto);
        CarReturn updatedCarReturn = carReturnService.updateCarReturnWithEmployee(id, employee);
        CarReturnDto response = carReturnMapper.toDto(updatedCarReturn);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
