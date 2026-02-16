package com.salesianostriana.dam.precioustime.project.controller;

import com.salesianostriana.dam.precioustime.project.dto.CreateProjectRequest;
import com.salesianostriana.dam.precioustime.project.dto.EditProjectRequest;
import com.salesianostriana.dam.precioustime.project.dto.ProjectResponse;
import com.salesianostriana.dam.precioustime.project.service.ProjectService;
import com.salesianostriana.dam.precioustime.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
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

    @GetMapping("/admin")
    public Page<ProjectResponse> getAllProjects(@PageableDefault Pageable pageable) {
        return projectService.findAllProjects(pageable)
                .map(ProjectResponse::of);
    }

    @PostFilter("""
        filterObject.author.username == authentication.principal.username or hasRole('ADMIN')
        """)
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<ProjectResponse> editProject(@PathVariable Long id, @Valid @RequestBody EditProjectRequest cmd) {
        return ResponseEntity.ok(ProjectResponse.of(projectService.editProject(id, cmd)));
    }

    @PostFilter("""
        filterObject.author.username == authentication.principal.username or hasRole('ADMIN')
        """)
    @PutMapping("/cancel/{id:[0-9]+}")
    public ResponseEntity<ProjectResponse> cancelProject(@PathVariable Long id) {
        return ResponseEntity.ok(ProjectResponse.of(projectService.cancelProject(id)));
    }

}
