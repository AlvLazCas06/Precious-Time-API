package com.salesianostriana.dam.precioustime.project.service;

import com.salesianostriana.dam.precioustime.project.dto.CreateProjectRequest;
import com.salesianostriana.dam.precioustime.project.dto.EditProjectRequest;
import com.salesianostriana.dam.precioustime.project.dto.ProjectSpecDTO;
import com.salesianostriana.dam.precioustime.project.exception.ProjectNotFoundException;
import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.model.ProjectStatus;
import com.salesianostriana.dam.precioustime.project.repository.ProjectRepository;
import com.salesianostriana.dam.precioustime.project.spec.ProjectSpec;
import com.salesianostriana.dam.precioustime.shared.exception.BadRequestException;
import com.salesianostriana.dam.precioustime.user.exception.UserNotFoundException;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project saveProject(CreateProjectRequest cmd) {
        Project project = cmd.toEntity();
        project.setStatus(ProjectStatus.EN_PROCESO);
        return projectRepository.save(project);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Page<Project> findProjectsByAuthor(Pageable pageable, String author) {
        Page<Project> projects = projectRepository.findByAuthor(pageable, author);
        if (projects.isEmpty()) {
            throw new ProjectNotFoundException("El usuario %s no tiene proyectos creados".formatted(author));
        }
        return projects;
    }

    public Page<Project> findAllProjects(Pageable pageable, ProjectSpecDTO spec) {
        Page<Project> projects = projectRepository.findBy(
                PredicateSpecification.allOf(
                        ProjectSpec.specName(spec.name()),
                        ProjectSpec.specStatus(spec.status())
                ),
                q -> q.page(pageable)
        );
        if (projects.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        return projects;
    }

    public Project editProject(Long id, EditProjectRequest cmd) {
        return projectRepository.findById(id)
                .map(project -> {
                    if (project.getStatus() == ProjectStatus.CANCELADO) {
                        throw new BadRequestException();
                    }
                    project.setName(cmd.name());
                    project.setDescription(cmd.description());
                    project.changeProgress();
                    if (project.getProgress().doubleValue() == 100) {
                        project.setStatus(ProjectStatus.COMPLETADO);
                    }
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project cancelProject(Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    if (project.getStatus() == ProjectStatus.CANCELADO) {
                        throw new BadRequestException("No se puede cancelar un proyecto que ya está cancelado.");
                    }
                    project.setStatus(ProjectStatus.CANCELADO);
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ProjectNotFoundException(id));
    }

}
