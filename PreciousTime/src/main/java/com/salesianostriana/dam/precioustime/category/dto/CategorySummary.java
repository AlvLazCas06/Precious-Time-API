package com.salesianostriana.dam.precioustime.category.dto;

import com.salesianostriana.dam.precioustime.category.model.Category;

public record CategorySummary(
        Long id,
        String name,
        String emoji,
        String color
) {

    public static CategorySummary of(Category category) {
        return new CategorySummary(
                category.getId(),
                category.getName(),
                category.getEmoji(),
                category.getColor()
        );
    }

}
