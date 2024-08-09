package com.example.franchiseapi.services;

import com.example.franchiseapi.dto.FranchiseRequestDTO;
import com.example.franchiseapi.dto.FranchiseResponseDTO;
import com.example.franchiseapi.entity.Branch;
import com.example.franchiseapi.entity.Franchise;
import com.example.franchiseapi.entity.Product;
import com.example.franchiseapi.exception.CustomException;
import com.example.franchiseapi.repository.BranchRepository;
import com.example.franchiseapi.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    private final BranchRepository branchRepository;

    @Autowired
    public FranchiseService(FranchiseRepository franchiseRepository, BranchRepository branchRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
    }

    public FranchiseResponseDTO addFranchise(FranchiseRequestDTO franchiseRequestDTO) {
        if (franchiseRepository.existsByName(franchiseRequestDTO.getName())) {
            throw new CustomException.FranchiseAlreadyExistsException();
        }

        Franchise franchise = mapperFranchise(franchiseRequestDTO);
        Franchise savedFranchise = this.franchiseRepository.save(franchise);
        return new FranchiseResponseDTO(savedFranchise.getId(), savedFranchise.getName());
    }

    public Branch addBranchToFranchise(Long franchiseId, Branch branch) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(CustomException.FranchiseIdNotFoundException::new);

        branch.setFranchise(franchise);
        return branchRepository.save(branch);
    }

    public List<Product> getTopProductsByStockInEachBranch(Long franchiseId) {
        return branchRepository.findTopProductsByStockInEachBranch(franchiseId);
    }

    public FranchiseResponseDTO updateFranchiseName(Long id, String newName) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(CustomException.FranchiseIdNotFoundException::new);
        
        if (franchiseRepository.existsByName(newName)) {
            throw new CustomException.FranchiseAlreadyExistsException();
        }

        franchise.setName(newName);
        Franchise updatedFranchise = franchiseRepository.save(franchise);
        return new FranchiseResponseDTO(updatedFranchise.getId(), updatedFranchise.getName());
    }

    private Franchise mapperFranchise(FranchiseRequestDTO franchiseRequestDTO) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseRequestDTO.getName());
        return franchise;
    }
}
