package com.example.franchiseapi.services;

import com.example.franchiseapi.dto.BranchResponseDTO;
import com.example.franchiseapi.dto.ProductRequestDTO;
import com.example.franchiseapi.dto.ProductResponseDTO;
import com.example.franchiseapi.entity.Branch;
import com.example.franchiseapi.entity.Product;
import com.example.franchiseapi.exception.CustomException;
import com.example.franchiseapi.repository.BranchRepository;
import com.example.franchiseapi.repository.FranchiseRepository;
import com.example.franchiseapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    private final ProductRepository productRepository;

    private final FranchiseRepository franchiseRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository, ProductRepository productRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.franchiseRepository = franchiseRepository;
    }

    public ProductResponseDTO addProductToBranch(Long branchId, ProductRequestDTO productRequestDTO) {

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(CustomException.BranchIdNotFoundException::new);

        Product product = mapperProduct(productRequestDTO);
        product.setBranch(branch);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDTO(savedProduct.getName(), savedProduct.getStock());
    }

    public BranchResponseDTO updateBranchName(Long branchId, Long franchiseId, String newName) {

        franchiseRepository.findById(franchiseId).orElseThrow(() -> new RuntimeException("Franchise not found"));

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(CustomException.BranchIdNotFoundException::new);

        if (!branch.getFranchise().getId().equals(franchiseId)) {
            throw new CustomException.BrandBelongToFranchiseException();
        }

        if (branchRepository.existsByNameAndFranchiseId(newName, franchiseId)) {
            throw new CustomException.BranchAlreadyExistsException();
        }

        branch.setName(newName);
        Branch updatedBranch = branchRepository.save(branch);
        return new BranchResponseDTO(updatedBranch.getId(), updatedBranch.getName(), updatedBranch.getFranchise().getId());
    }

    private Product mapperProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setStock(productRequestDTO.getStock());
        return product;
    }

}