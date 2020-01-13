package com.sda10.carrental.controller;

import com.sda10.carrental.dto.BranchDto;
import com.sda10.carrental.dto.BranchMapper;
import com.sda10.carrental.model.Branch;
import com.sda10.carrental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;

    @PostMapping(value = "/branch")
    public BranchDto createRental(@RequestBody BranchDto branchDetails) {

        Branch branch = branchMapper.toEntity(branchDetails);

        branch = branchService.createBranch(branch);

        return branchMapper.toDto(branch);
    }

    @GetMapping(value = "/branch/{id}")
    public BranchDto findBranchById(@PathVariable Long id) {

        Branch branchById = branchService.findBranchById(id);

        return branchMapper.toDto(branchById);

    }

    @GetMapping(value = "/branch")
    public List<BranchDto> findAllBranches() {

        List<Branch> branches = branchService.getAllBranches();
        return branches
                .stream()
                .map(branchMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/branch/{id}")
    public ResponseEntity updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) {

        Branch branch = branchMapper.toEntity(branchDto);

        branchService.updateBranch(id, branch);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/branch/{id}")
    public ResponseEntity deleteBranch(@PathVariable Long id) {

        branchService.deleteBranch(id);

        return new ResponseEntity(HttpStatus.OK);
    }


}
