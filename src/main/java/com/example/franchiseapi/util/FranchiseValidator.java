package com.example.franchiseapi.util;

import com.example.franchiseapi.entity.Franchise;
import com.example.franchiseapi.exception.CustomException;
import com.example.franchiseapi.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FranchiseValidator {

    private final FranchiseRepository franchiseRepository;

    @Autowired
    public FranchiseValidator(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Franchise validateFranchiseExists(Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new CustomException.FranchiseIdNotFoundException(franchiseId));
    }

    public void validateFranchiseNotExists(String name) {
        if (franchiseRepository.existsByName(name)) {
            throw new CustomException.FranchiseAlreadyExistsException(name);
        }
    }
}