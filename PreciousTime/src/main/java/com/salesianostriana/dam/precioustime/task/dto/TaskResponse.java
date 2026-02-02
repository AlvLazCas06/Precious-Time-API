package com.salesianostriana.dam.precioustime.task.dto;

import com.salesianostriana.dam.precioustime.category.dto.CategorySummary;
import com.salesianostriana.dam.precioustime.task.model.Task;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status,
        String priority,
        CategorySummary category,
        String projectName,
        LocalDateTime completedAt
) {

    public static TaskResponse of(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name().toLowerCase(),
                task.getPriority().name().toLowerCase(),
                task.getCategory() != null ? CategorySummary.of(task.getCategory()) : null,
                task.getProject() != null ? task.getProject().getName() : "No tiene un proyecto asignado",
                task.getCompletedAt() != null ? task.getCompletedAt() : null
        );
    }

}
