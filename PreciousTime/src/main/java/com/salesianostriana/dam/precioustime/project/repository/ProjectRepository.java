package com.salesianostriana.dam.precioustime.project.repository;

import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.model.ProjectStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ProjectRepository
        extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @EntityGraph(attributePaths = {"tasks", "tasks.category"})
    List<Project> findByAuthor(String author);

    @EntityGraph(attributePaths = {"tasks", "tasks.category"})
    Page<Project> findByAuthorAndStatus(String author, ProjectStatus status, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "tasks")
    default <S extends Project, R> R findBy(PredicateSpecification<Project> spec, Function<? super SpecificationFluentQuery<S>, R> queryFunction) {
        return JpaSpecificationExecutor.super.findBy(spec, queryFunction);
    }

    @Override
    @EntityGraph(attributePaths = {"tasks", "tasks.category"})
    Optional<Project> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"tasks", "tasks.category"})
    List<Project> findAll();

}
