package com.salesianostriana.dam.precioustime.project.controller;

import com.salesianostriana.dam.precioustime.project.dto.*;
import com.salesianostriana.dam.precioustime.project.service.ProjectService;
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

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest cmd) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProjectResponse.of(projectService.saveProject(cmd)));
    }

    @GetMapping
    public Page<ProjectResponse> getProjects(@PageableDefault Pageable pageable, @AuthenticationPrincipal User user) {
        return projectService.findProjectsByAuthor(pageable, user.getUsername())
                .map(ProjectResponse::of);
    }

    @GetMapping("/summary")
    public Page<ProjectSummaryDto> getSummaryProjects(@PageableDefault(size = 3, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User user) {
        return projectService.findProjectsByAuthor(pageable, user.getUsername())
                .map(ProjectSummaryDto::of);
    }

    @GetMapping("/admin")
    public Page<ProjectResponse> getAllProjects(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status
    ) {
        return projectService.findAllProjects(pageable, new ProjectSpecDTO(name, status))
                .map(ProjectResponse::of);
    }

    @PostAuthorize("""
        returnObject.author == authentication.principal.username or hasRole('ADMIN')
        """)
    @PutMapping("/{id:[0-9]+}")
    public ProjectResponse editProject(@PathVariable Long id, @Valid @RequestBody EditProjectRequest cmd) {
        return ProjectResponse.of(projectService.editProject(id, cmd));
    }

    @PostAuthorize("""
        returnObject.author == authentication.principal.username or hasRole('ADMIN')
        """)
    @PutMapping("/cancel/{id:[0-9]+}")
    public ProjectResponse cancelProject(@PathVariable Long id) {
        return ProjectResponse.of(projectService.cancelProject(id));
    }

}
