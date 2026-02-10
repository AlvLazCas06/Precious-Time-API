package com.salesianostriana.dam.precioustime.task.service;

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

    public Task saveTask(CreateTaskRequest createTaskRequest) {
        Task task = createTaskRequest.toEntity();
        if (createTaskRequest.projectId() != null) {
            try {
                Project project = projectService.findById(createTaskRequest.projectId());
            } catch (ProjectNotFoundException e) {
                throw new BadRequestException();
            }
        }
        return taskRepository.save(task);
    }

}
