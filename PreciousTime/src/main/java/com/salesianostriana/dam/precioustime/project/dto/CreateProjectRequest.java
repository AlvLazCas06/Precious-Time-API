package com.salesianostriana.dam.precioustime.project.dto;

import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.model.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProjectRequest(
        @NotBlank
        String name,
        String description,
        @NotNull
        ProjectStatus status,
        @NotNull
        Long userId
) {

    public Project toEntity() {
        return Project.builder()
                .name(name)
                .description(description)
                .status(status)
                .build();
    }

}
