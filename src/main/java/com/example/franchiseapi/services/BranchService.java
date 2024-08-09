package com.example.franchiseapi.services;

import com.example.franchiseapi.dto.BranchResponseDTO;
import com.example.franchiseapi.entity.Branch;
import com.example.franchiseapi.repository.BranchRepository;
import com.example.franchiseapi.util.BranchValidator;
import com.example.franchiseapi.util.FranchiseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchValidator branchValidator;
    private final FranchiseValidator franchiseValidator;

    @Autowired
    public BranchService(BranchRepository branchRepository, BranchValidator branchValidator, FranchiseValidator franchiseValidator) {
        this.branchRepository = branchRepository;
        this.branchValidator = branchValidator;
        this.franchiseValidator = franchiseValidator;
    }

    public BranchResponseDTO updateBranchName(Long branchId, Long franchiseId, String newName) {
        franchiseValidator.validateFranchiseExists(franchiseId);
        Branch branch = branchValidator.validateBranchExists(branchId);
        branchValidator.validateBranchBelongsToFranchise(branch, franchiseId);
        branchValidator.validateBranchNameUnique(newName, franchiseId);

        branch.setName(newName);
        Branch updatedBranch = branchRepository.save(branch);

        return new BranchResponseDTO(updatedBranch.getId(), updatedBranch.getName(), updatedBranch.getFranchise().getId());
    }
}