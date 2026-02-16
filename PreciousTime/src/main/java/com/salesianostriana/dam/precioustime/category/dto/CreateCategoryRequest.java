package com.salesianostriana.dam.precioustime.category.dto;

import com.salesianostriana.dam.precioustime.category.model.Category;
import com.salesianostriana.dam.precioustime.category.validation.annotation.UniqueCategoryName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank
        @UniqueCategoryName
        String name,
        @NotBlank
        @Max(2)
        String emoji,
        @NotBlank
        String color
) {

    public Category toEntity() {
        return Category.builder()
                .name(name.toLowerCase())
                .emoji(emoji)
                .color(color)
                .build();
    }

}
