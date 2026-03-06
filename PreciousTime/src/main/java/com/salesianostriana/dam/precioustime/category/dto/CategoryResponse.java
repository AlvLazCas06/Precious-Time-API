package com.salesianostriana.dam.precioustime.category.dto;

import com.salesianostriana.dam.precioustime.category.model.Category;

public record CategoryResponse(
        Long id,
        String name,
        String emoji,
        String color
) {

    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getEmoji(),
                category.getColor()
        );
    }

}
