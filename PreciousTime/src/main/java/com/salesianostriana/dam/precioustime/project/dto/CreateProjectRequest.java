package com.salesianostriana.dam.precioustime.project.dto;

import com.salesianostriana.dam.precioustime.project.model.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateProjectRequest(
        @NotBlank
        @Size(min = 8, max = 50)
        String name,
        @Size(max = 2000)
        String description,
        @NotNull
        LocalDate finishDate
) {

    public Project toEntity() {
        return Project.builder()
                .name(name)
                .description(description)
                .build();
    }

}
