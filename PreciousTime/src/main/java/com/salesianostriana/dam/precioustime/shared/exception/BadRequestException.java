package com.salesianostriana.dam.precioustime.shared.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super("Los parámetros introducidos no son válidos.");
    }

}
