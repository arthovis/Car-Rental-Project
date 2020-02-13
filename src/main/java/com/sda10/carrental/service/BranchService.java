package com.sda10.carrental.service;

import com.sda10.carrental.model.Branch;
import com.sda10.carrental.model.Car;
import com.sda10.carrental.model.Employee;
import com.sda10.carrental.model.Status;
import com.sda10.carrental.repository.BranchRepository;
import com.sda10.carrental.repository.CarRepository;
import com.sda10.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch findBranchById(Long id) {
        return branchRepository.getOne(id);
    }

    public Branch updateBranch(Long id, Branch branch) {
        Optional<Branch> branchToUpdate = branchRepository.findById(id);

        validateAllCarsAreAvailable(branch);

        if (branchToUpdate.isPresent()) {
            branch.setId(id);
            return branchRepository.save(branch);
        } else {
            throw new RuntimeException();
        }
    }

    private void validateAllCarsAreAvailable(Branch branch) {
        for (Car car : branch.getAvailableCarsList()) {
            if (!Status.AVAILABLE.equals(car.getStatus())) {
                throw new RuntimeException();
            }
        }
    }

    public void deleteBranch(Long id) {
        Branch existingBranch = branchRepository.findById(id).get();

        branchRepository.delete(existingBranch);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public List<Branch> getAllBranches(Integer pageIndex, Integer pageSize) {
        Pageable paging = PageRequest.of(pageIndex, pageSize);

        Page<Branch> pagedResult = branchRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public Branch updateBranchWithEmployee(Long id, Employee employee) {
        Optional<Branch> branchToUpdate = branchRepository.findById(id);

        if (branchToUpdate.isPresent()) {
            Branch branch = branchToUpdate.get();
            branch.addEmployee(employee);
            employeeRepository.save(employee);
            return branchRepository.save(branch);
        } else {
            throw new RuntimeException("Branch could not be updated");
        }
    }

    public Branch updateBranchWithoutEmployee(Long id, Employee employee) {
        Optional<Branch> branchToUpdate = branchRepository.findById(id);

        if (branchToUpdate.isPresent()) {
            Branch branch = branchToUpdate.get();
            branch.removeEmployee(employee);
            employeeRepository.save(employee);
            return branchRepository.save(branch);
        } else {
            throw new RuntimeException("Branch could not be removed");
        }
    }

    public Branch updateBranchWithCar(Long id, Car car) {
        Optional<Branch> branchToUpdate = branchRepository.findById(id);

        if (branchToUpdate.isPresent()) {
            Branch branch = branchToUpdate.get();
            branch.addCar(car);
            return branchRepository.save(branch);
        } else {
            throw new RuntimeException("Branch could not be updated");
        }
    }

    public Branch updateBranchWithoutCar(Long id, Car car) {
        Optional<Branch> branchToUpdate = branchRepository.findById(id);

        if (branchToUpdate.isPresent()) {
            Branch branch = branchToUpdate.get();
            branch.removeCar(car);
            return branchRepository.save(branch);
        } else {
            throw new RuntimeException("Branch could not be removed");
        }
    }

}
