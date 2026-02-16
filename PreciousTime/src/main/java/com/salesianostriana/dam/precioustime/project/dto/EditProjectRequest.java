package com.salesianostriana.dam.precioustime.project.dto;

import jakarta.validation.constraints.NotBlank;

public record EditProjectRequest(
        @NotBlank
        String name,
        String description

) {
}
