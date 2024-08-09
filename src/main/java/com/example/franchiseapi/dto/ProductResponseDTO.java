package com.example.franchiseapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Stock cannot be null")
    private Integer stock;

}
