package com.salesianostriana.dam.precioustime.task.repository;

import com.salesianostriana.dam.precioustime.task.model.Task;
import com.salesianostriana.dam.precioustime.task.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByAuthor(Pageable pageable, String author);

    Page<Task> findByAuthorAndStatus(String author, TaskStatus status, Pageable pageable);


}
