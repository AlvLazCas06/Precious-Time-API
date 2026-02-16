package com.salesianostriana.dam.precioustime.project.repository;

import com.salesianostriana.dam.precioustime.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByAuthor(Pageable pageable, String author);

}
