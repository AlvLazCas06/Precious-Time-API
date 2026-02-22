package com.salesianostriana.dam.precioustime.task.controller;

import com.salesianostriana.dam.precioustime.task.dto.CreateTaskRequest;
import com.salesianostriana.dam.precioustime.task.dto.TaskResponse;
import com.salesianostriana.dam.precioustime.task.service.TaskService;
import com.salesianostriana.dam.precioustime.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest cmd) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TaskResponse.of(taskService.saveTask(cmd)));
    }

    @GetMapping
    public Page<TaskResponse> getTaskUser(
            @PageableDefault Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        return taskService.getTasks(pageable, user).map(TaskResponse::of);
    }

}
