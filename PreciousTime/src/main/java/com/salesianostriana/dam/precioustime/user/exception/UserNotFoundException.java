package com.salesianostriana.dam.precioustime.user.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        super("El usuario con el id: %d, no existe".formatted(id));
    }

}
