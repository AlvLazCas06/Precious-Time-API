package com.salesianostriana.dam.precioustime.task.dto;

import com.salesianostriana.dam.precioustime.task.model.Task;

public record TaskSummaryDTO(
        Long id,
        String title,
        String categoryName,
        String priority
) {

    public static TaskSummaryDTO of(Task task) {
        return new TaskSummaryDTO(
                task.getId(),
                task.getTitle(),
                task.getCategory() != null ? task.getCategory().getName() : "No tiene categoría",
                task.getPriority().name().toLowerCase()
        );
    }

}
