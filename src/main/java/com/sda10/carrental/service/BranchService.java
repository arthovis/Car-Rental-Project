package com.sda10.carrental.service;

import com.sda10.carrental.model.Branch;
import com.sda10.carrental.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Transactional
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch findBranchById(Long id) {
        return branchRepository.getOne(id);
    }

    public Branch updateBranch(Long id, Branch branch) {
        Optional<Branch> branchToUpdate = branchRepository.findById(id);

        if (branchToUpdate.isPresent()) {
            branch.setId(id);
            return branchRepository.save(branch);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteBranch(Long id) {
        Branch existingBranch = branchRepository.findById(id).get();

        branchRepository.delete(existingBranch);
    }
}
