package com.example.franchiseapi.controller;

import com.example.franchiseapi.entity.Franchise;
import com.example.franchiseapi.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise newFranchise = franchiseService.addFranchise(franchise);
        return new ResponseEntity<>(newFranchise, HttpStatus.CREATED);
    }
}