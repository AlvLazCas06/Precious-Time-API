package com.salesianostriana.dam.precioustime.category.exception;

public class CategoryDuplicatedException extends RuntimeException {
    public CategoryDuplicatedException(String message) {
        super(message);
    }

    public CategoryDuplicatedException() {
        super("Ya existe una categoría con ese nombre");
    }

}
