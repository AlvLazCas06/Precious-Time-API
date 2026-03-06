package com.salesianostriana.dam.precioustime.task.dto;

import com.salesianostriana.dam.precioustime.task.model.Priority;
import com.salesianostriana.dam.precioustime.task.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public record CreateTaskRequest(
        @NotBlank
        String title,
        String description,
        @NotBlank
        String priority,
        @NotNull
        Long categoryId,
        Long projectId,
        @NotNull
        LocalDateTime completedAt
) {

    public Task toEntity() {
        log.info(completedAt.toString());
        return Task.builder()
                .title(title)
                .description(description)
                .priority(Priority.valueOf(priority.toUpperCase()))
                .completedAt(completedAt)
                .build();
    }

}
