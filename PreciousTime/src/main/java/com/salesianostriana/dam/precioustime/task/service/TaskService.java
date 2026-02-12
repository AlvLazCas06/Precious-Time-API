package com.salesianostriana.dam.precioustime.task.service;

import com.salesianostriana.dam.precioustime.category.exception.CategoryNotFoundException;
import com.salesianostriana.dam.precioustime.category.model.Category;
import com.salesianostriana.dam.precioustime.category.service.CategoryService;
import com.salesianostriana.dam.precioustime.project.exception.ProjectNotFoundException;
import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.service.ProjectService;
import com.salesianostriana.dam.precioustime.shared.exception.BadRequestException;
import com.salesianostriana.dam.precioustime.task.dto.CreateTaskRequest;
import com.salesianostriana.dam.precioustime.task.model.Task;
import com.salesianostriana.dam.precioustime.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
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
            throw new BadRequestException();
        }
        if (cmd.projectId() != null) {
            try {
                Project project = projectService.findById(cmd.projectId());
                project.addTask(task);
            } catch (ProjectNotFoundException e) {
                throw new BadRequestException();
            }
        }
        return taskRepository.save(task);
    }

}
