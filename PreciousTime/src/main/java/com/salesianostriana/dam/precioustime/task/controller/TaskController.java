package com.salesianostriana.dam.precioustime.task.controller;

import com.salesianostriana.dam.precioustime.task.dto.CreateTaskRequest;
import com.salesianostriana.dam.precioustime.task.dto.EditTaskRequest;
import com.salesianostriana.dam.precioustime.task.dto.TaskResponse;
import com.salesianostriana.dam.precioustime.task.dto.TaskSummaryHomeDTO;
import com.salesianostriana.dam.precioustime.task.service.TaskService;
import com.salesianostriana.dam.precioustime.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Page<TaskResponse> getTasksUser(
            @PageableDefault(sort = {"status"}, direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        return taskService.getTasks(pageable, user).map(TaskResponse::of);
    }

    @GetMapping("/admin")
    public Page<TaskResponse> getAllTask(@PageableDefault Pageable pageable) {
        return taskService.getAllTask(pageable).map(TaskResponse::of);
    }

    @PostAuthorize("returnObject.author() == authentication.principal.username or hasRole('ADMIN')")
    @GetMapping("/{id:[0-9]+}")
    public TaskResponse getTask(@PathVariable Long id) {
        return TaskResponse.of(taskService.getById(id));
    }

    @GetMapping("/summary")
    public Page<TaskSummaryHomeDTO> getTaskSummary(
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        return taskService.getNotCompleted(pageable, user)
                .map(TaskSummaryHomeDTO::of);
    }

    @PutMapping("/{id:[0-9]+}")
    public TaskResponse editTask(@PathVariable Long id, @RequestBody EditTaskRequest cmd) {
        return TaskResponse.of(taskService.editTask(id, cmd));
    }

    @PatchMapping("/{id:[0-9]+}/completed")
    public TaskResponse checkCompleted(@PathVariable Long id) {
        return TaskResponse.of(taskService.checkCompleted(id));
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/all")
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks()
                .stream().map(TaskResponse::of)
                .toList();
    }

}
