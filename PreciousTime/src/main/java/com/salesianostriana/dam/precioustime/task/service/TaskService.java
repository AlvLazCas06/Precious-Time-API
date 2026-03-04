package com.salesianostriana.dam.precioustime.task.service;

import com.salesianostriana.dam.precioustime.category.exception.CategoryNotFoundException;
import com.salesianostriana.dam.precioustime.category.model.Category;
import com.salesianostriana.dam.precioustime.category.service.CategoryService;
import com.salesianostriana.dam.precioustime.project.exception.ProjectNotFoundException;
import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.service.ProjectService;
import com.salesianostriana.dam.precioustime.shared.exception.BadRequestException;
import com.salesianostriana.dam.precioustime.task.dto.CreateTaskRequest;
import com.salesianostriana.dam.precioustime.task.dto.EditTaskRequest;
import com.salesianostriana.dam.precioustime.task.exception.TaskNotFoundException;
import com.salesianostriana.dam.precioustime.task.model.Priority;
import com.salesianostriana.dam.precioustime.task.model.Task;
import com.salesianostriana.dam.precioustime.task.model.TaskStatus;
import com.salesianostriana.dam.precioustime.task.repository.TaskRepository;
import com.salesianostriana.dam.precioustime.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final CategoryService categoryService;

    public Task saveTask(CreateTaskRequest cmd) {
        Task task = cmd.toEntity();
        try {
            Category category = categoryService.getById(cmd.categoryId());
            task.setCategory(category);
        } catch (CategoryNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        if (cmd.projectId() != null) {
            try {
                Project project = projectService.findById(cmd.projectId());
                project.addTask(task);
            } catch (ProjectNotFoundException e) {
                throw new BadRequestException(e.getMessage());
            }
        }
        task.setStatus(TaskStatus.PENDIENTE);
        return taskRepository.save(task);
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).
                orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Page<Task> getTasks(Pageable pageable, User user) {
        Page<Task> tasks = taskRepository.findByAuthor(pageable, user.getUsername());
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return tasks;
    }

    public Page<Task> getNotCompleted(Pageable pageable, User user) {
        Page<Task> tasks = taskRepository.findByAuthorAndStatus(user.getUsername(), TaskStatus.PENDIENTE, pageable);
        if (tasks.isEmpty())
            throw new TaskNotFoundException();
        return tasks;
    }

    public Page<Task> getAllTask(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Aún no se han creado tareas");
        }
        return tasks;
    }

    public Task checkCompleted(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    if (task.getStatus().equals(TaskStatus.COMPLETADO)) {
                        throw new BadRequestException("No se puede marcar como completado una tarea que ya lo está");
                    }
                    task.setStatus(TaskStatus.COMPLETADO);
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task editTask(Long id, EditTaskRequest cmd) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(cmd.title());
                    task.setDescription(cmd.description());
                    task.setPriority(Priority.valueOf(cmd.priority().toUpperCase()));
                    task.setCompletedAt(cmd.completedAt());
                    try {
                        Category category = categoryService.getById(cmd.categoryId());
                        task.setCategory(category);
                    } catch (CategoryNotFoundException e) {
                        throw new BadRequestException(e.getMessage());
                    }
                    if (cmd.projectId() != null) {
                        try {
                            Project project = projectService.findById(cmd.projectId());
                            project.addTask(task);
                        } catch (ProjectNotFoundException e) {
                            throw new BadRequestException(e.getMessage());
                        }
                    }
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (task.getProject() != null) {
            task.getProject().deleteTask(task);
        }
        taskRepository.delete(task);
    }

}
