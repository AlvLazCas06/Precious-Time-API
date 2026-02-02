package com.salesianostriana.dam.precioustime.task.dto;

import com.salesianostriana.dam.precioustime.task.model.Priority;
import com.salesianostriana.dam.precioustime.task.model.Task;

public record CreateTaskRequest(
        String title,
        String description,
        String priority,
        Long categoryId,
        Long projectId
) {

    public Task toEntity() {
        return Task.builder()
                .title(title)
                .description(description)
                .priority(Priority.valueOf(priority.toUpperCase()))
                .build();
    }

}
