package com.salesianostriana.dam.precioustime.task.dto;

import java.time.LocalDateTime;

public record EditTaskRequest(
        String title,
        String description,
        String priority,
        Long categoryId,
        Long projectId,
        LocalDateTime completedAt
) {
}
