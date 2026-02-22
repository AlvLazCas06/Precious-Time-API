package com.salesianostriana.dam.precioustime.project.dto;

import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.model.ProjectStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateProjectRequest(
        @NotBlank
        //@Min(8)
        String name,
        String description
) {

    public Project toEntity() {
        return Project.builder()
                .name(name)
                .description(description)
                .build();
    }

}
