package com.example.franchiseapi.unit;

import com.example.franchiseapi.repository.FranchiseRepository;
import com.example.franchiseapi.services.IFranchiseService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private IFranchiseService franchiseService;

}
