package com.example.franchiseapi.services;

import com.example.franchiseapi.entity.Franchise;
import com.example.franchiseapi.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Autowired
    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Franchise addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }
}
