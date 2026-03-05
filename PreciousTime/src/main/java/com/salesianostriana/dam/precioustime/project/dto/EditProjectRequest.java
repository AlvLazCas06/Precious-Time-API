package com.salesianostriana.dam.precioustime.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EditProjectRequest(
        @NotBlank
        @Size(min = 8, max = 50)
        String name,
        @Size(max = 200)
        String description,
        @NotNull
        LocalDate finishDate
) {
}
