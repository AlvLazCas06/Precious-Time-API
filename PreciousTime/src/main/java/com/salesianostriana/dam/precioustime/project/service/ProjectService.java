package com.salesianostriana.dam.precioustime.project.service;

import com.salesianostriana.dam.precioustime.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

}
