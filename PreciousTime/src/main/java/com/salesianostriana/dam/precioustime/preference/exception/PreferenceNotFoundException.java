package com.salesianostriana.dam.precioustime.preference.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

public class PreferenceNotFoundException extends NotFoundException {
    public PreferenceNotFoundException(String message) {
        super(message);
    }

    public PreferenceNotFoundException() {
        super("Las preferencias de usuario no han sido encontradas.");
    }

}
