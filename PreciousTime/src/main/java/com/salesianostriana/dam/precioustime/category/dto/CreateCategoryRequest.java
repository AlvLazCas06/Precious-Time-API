package com.salesianostriana.dam.precioustime.category.dto;

import com.salesianostriana.dam.precioustime.category.model.Category;
import com.salesianostriana.dam.precioustime.category.validation.annotation.UniqueCategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank(message = "{createCategoryRequest.name.notBlank}")
        @UniqueCategoryName
        String name,
        @NotBlank(message = "{createCategoryRequest.emoji.notBlank}")
        @Size(min = 2, max = 2, message = "{createCategoryRequest.emoji.size}")
        String emoji,
        @NotBlank(message = "{createCategoryRequest.color.notBlank}")
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
