package com.salesianostriana.dam.precioustime.project.dto;

import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.task.dto.TaskSummaryDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime finishDate,
        String status,
        BigDecimal progress,
        List<TaskSummaryDTO> tasks
) {

    public static ProjectResponse of(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription() != null
                        ? project.getDescription()
                        : "",
                project.getStartDate() != null
                        ? project.getStartDate()
                        : null,
                project.getFinishDate() != null
                        ? project.getFinishDate()
                        : null,
                project.getStatus().name()
                        .toLowerCase()
                        .replace("_", " "),
                project.getProgress(),
                project.getTasks() != null
                        ? project.getTasks()
                            .stream()
                            .map(TaskSummaryDTO::of)
                            .toList()
                        : null
        );
    }

}
