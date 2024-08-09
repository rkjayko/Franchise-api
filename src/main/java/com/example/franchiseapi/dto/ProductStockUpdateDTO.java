package com.example.franchiseapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ProductStockUpdateDTO {

    @NotNull(message = "Stock cannot be null")
    private Integer stock;
}