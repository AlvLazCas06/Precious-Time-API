package com.salesianostriana.dam.precioustime.project.exception;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;

public class ProjectNotFoundException extends NotFoundException {
    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException() {
        super("No hay proyectos en la base de datos");
    }

    public ProjectNotFoundException(Long id) {
        super("El proyecto con el id: %d, no existe".formatted(id));
    }

}
