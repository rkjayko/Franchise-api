package com.example.franchiseapi.services;

import com.example.franchiseapi.entity.Branch;
import com.example.franchiseapi.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }
}