package com.salesianostriana.dam.precioustime.project.dto;

import com.salesianostriana.dam.precioustime.project.model.Project;

import java.math.BigDecimal;

public record ProjectSummaryDto(
        Long id,
        String name,
        BigDecimal percent
) {

    public static ProjectSummaryDto of(Project project) {
        return new ProjectSummaryDto(
                project.getId(),
                project.getName(),
                project.getProgress()
        );
    }

}
