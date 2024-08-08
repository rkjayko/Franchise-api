package com.example.franchiseapi.unit;

import com.example.franchiseapi.entity.Franchise;
import com.example.franchiseapi.repository.FranchiseRepository;
import com.example.franchiseapi.services.FranchiseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseService franchiseService;

    @Test
    public void testAddFranchise() {
        // Arrange
        Franchise franchise = new Franchise();
        franchise.setName("Test Franchise");
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

        // Act
        Franchise result = franchiseService.addFranchise(franchise);

        // Assert
        assertNotNull(result);
        assertEquals("Test Franchise", result.getName());
        verify(franchiseRepository, times(1)).save(franchise);
    }
}
