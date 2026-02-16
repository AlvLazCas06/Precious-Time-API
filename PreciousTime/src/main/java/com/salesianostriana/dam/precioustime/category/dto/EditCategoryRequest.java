package com.salesianostriana.dam.precioustime.category.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EditCategoryRequest(
        @NotBlank
        @Min(2) @Max(2)
        String emoji,
        @NotBlank
        String color
) {
}
