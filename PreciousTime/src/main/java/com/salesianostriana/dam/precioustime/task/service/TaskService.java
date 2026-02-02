package com.salesianostriana.dam.precioustime.task.service;

import com.salesianostriana.dam.precioustime.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

}
