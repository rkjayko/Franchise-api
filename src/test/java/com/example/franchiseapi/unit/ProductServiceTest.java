package com.example.franchiseapi.unit;

import com.example.franchiseapi.entity.Product;
import com.example.franchiseapi.repository.ProductRepository;
import com.example.franchiseapi.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setStock(100);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.addProduct(product);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(100, result.getStock());
        verify(productRepository, times(1)).save(product);
    }
}