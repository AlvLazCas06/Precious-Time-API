package com.salesianostriana.dam.precioustime.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditCategoryRequest(
        @NotBlank
        @Size(min = 2, max = 2)
        String emoji,
        @NotBlank
        String color
) {
}
