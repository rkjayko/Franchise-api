package com.example.franchiseapi.controller;

import com.example.franchiseapi.dto.ProductStockUpdateDTO;
import com.example.franchiseapi.entity.Product;
import com.example.franchiseapi.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Product> updateProductStock(@PathVariable Long id,
                                                      @RequestBody @Valid ProductStockUpdateDTO productStockUpdateDTO) {
        Product updatedProduct = productService.updateProductStock(id, productStockUpdateDTO.getStock());
        return ResponseEntity.ok(updatedProduct);
    }
}
