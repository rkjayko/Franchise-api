package com.example.franchiseapi.util;

import com.example.franchiseapi.entity.Branch;
import com.example.franchiseapi.entity.Product;
import com.example.franchiseapi.exception.CustomException;
import com.example.franchiseapi.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product validateProductInBranch(Branch branch, Long productId) {
        return branch.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new CustomException.ProductIdNotFoundException(productId));
    }

    public Product validateProductExists(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException.ProductIdNotFoundException(productId));
    }

    public void validateProductNameUnique(String newName, Long branchId) {
        if (productRepository.existsByNameAndBranchId(newName, branchId)) {
            throw new CustomException.ProductAlreadyExistsException(newName);
        }
    }
}