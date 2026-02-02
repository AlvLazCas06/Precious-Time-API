package com.salesianostriana.dam.precioustime.category.dto;

import com.salesianostriana.dam.precioustime.category.model.Category;

public record CategorySummary(
        String name,
        String emoji,
        String color
) {

    public static CategorySummary of(Category category) {
        return new CategorySummary(
                category.getName(),
                category.getEmoji(),
                category.getColor()
        );
    }

}
