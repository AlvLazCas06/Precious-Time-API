package com.salesianostriana.dam.precioustime.task.dto;

import com.salesianostriana.dam.precioustime.category.dto.CategorySummary;
import com.salesianostriana.dam.precioustime.task.model.Task;

import java.time.LocalDateTime;

public record TaskSummaryHomeDTO(
        Long id,
        String title,
        CategorySummary category,
        String priority,
        LocalDateTime completedAt
) {

    public static TaskSummaryHomeDTO of(Task task) {
        return new TaskSummaryHomeDTO(
                task.getId(),
                task.getTitle(),
                task.getCategory() != null
                        ? CategorySummary.of(task.getCategory())
                        : null,
                task.getPriority().name(),
                task.getCompletedAt()
        );
    }

}
