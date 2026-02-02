package com.salesianostriana.dam.precioustime.reminder.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

public class ReminderNotFoundException extends NotFoundException {
    public ReminderNotFoundException(String message) {
        super(message);
    }

    public ReminderNotFoundException() {
        super("No hay notificaciones en la base de datos");
    }

}
