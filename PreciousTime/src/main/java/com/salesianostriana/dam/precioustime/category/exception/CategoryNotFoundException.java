package com.salesianostriana.dam.precioustime.category.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super("No se han encontrado categorías en la base de datos");
    }

    public CategoryNotFoundException(Long id) {
        super("La categoría con el id: %d, no existe".formatted(id));
    }

}
