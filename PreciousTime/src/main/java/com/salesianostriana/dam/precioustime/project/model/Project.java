package com.salesianostriana.dam.precioustime.project.model;

import com.salesianostriana.dam.precioustime.task.model.Task;
import com.salesianostriana.dam.precioustime.task.model.TaskStatus;
import com.salesianostriana.dam.precioustime.user.model.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @CreatedDate
    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Builder.Default
    private BigDecimal progress = new BigDecimal(0);

    @OneToMany(mappedBy = "project")
    @Builder.Default
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();

    @CreatedBy
    private String author;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setProject(this);
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
        task.setProject(null);
    }

    public void changeProgress() {
        this.progress = BigDecimal.valueOf(
                this.tasks.stream()
                        .filter(task -> task.getStatus().equals(TaskStatus.COMPLETADO))
                        .count() / this.tasks.stream().count());
    }

}
