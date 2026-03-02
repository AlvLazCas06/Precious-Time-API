package com.salesianostriana.dam.precioustime.project.dto;

import com.salesianostriana.dam.precioustime.project.model.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProjectRequest(
        @NotBlank
        @Size(min = 8)
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
