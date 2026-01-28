package com.salesianostriana.dam.precioustime.project.model;

import com.salesianostriana.dam.precioustime.task.model.Task;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private BigDecimal progress;

    @OneToMany(mappedBy = "project")
    @Builder.Default
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();

}
