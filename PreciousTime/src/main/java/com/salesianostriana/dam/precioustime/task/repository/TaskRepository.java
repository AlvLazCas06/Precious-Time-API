package com.salesianostriana.dam.precioustime.task.repository;

import com.salesianostriana.dam.precioustime.task.model.Task;
import com.salesianostriana.dam.precioustime.task.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @EntityGraph(attributePaths = {"project.tasks", "category"})
    List<Task> findByAuthor(String author);

    @EntityGraph(attributePaths = {"project.tasks", "category"})
    Page<Task> findByAuthorAndStatus(String author, TaskStatus status, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"project.tasks", "category"})
    Optional<Task> findById(Long id);


}
