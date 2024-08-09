package com.example.franchiseapi.services;

import com.example.franchiseapi.entity.Branch;
import com.example.franchiseapi.entity.Product;
import com.example.franchiseapi.exception.CustomException;
import com.example.franchiseapi.repository.BranchRepository;
import com.example.franchiseapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final BranchRepository branchRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public void deleteProduct(Long branchId, Long productId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(CustomException.BranchIdNotFoundException::new);

        // Buscar el producto en la sucursal
        Product product = branch.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(CustomException.ProductIdNotFoundException::new);

        // Eliminar el producto de la sucursal
        branch.getProducts().remove(product);
        branchRepository.save(branch);
    }

    public Product updateProductStock(Long productId, Integer newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

        product.setStock(newStock);
        return productRepository.save(product);
    }
}
