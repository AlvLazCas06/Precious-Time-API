package com.salesianostriana.dam.precioustime.user.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(UUID id) {
        super("El usuario con el id: %d, no existe".formatted(id));
    }

}
