package com.salesianostriana.dam.precioustime.task.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

public class TaskNotFoundException extends NotFoundException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException() {
        super("No existen tareas en la base de datos");
    }

    public  TaskNotFoundException(Long id) {
        super("La tarea con el id: %d, no existe".formatted(id));
    }

}
