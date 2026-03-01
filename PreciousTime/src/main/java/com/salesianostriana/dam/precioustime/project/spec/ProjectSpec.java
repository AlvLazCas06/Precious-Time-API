package com.salesianostriana.dam.precioustime.project.spec;

import com.salesianostriana.dam.precioustime.project.model.Project;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.PredicateSpecification;

public interface ProjectSpec {

    static PredicateSpecification<Project> specName(String name) {
        return (from, cb) -> {
            from.fetch("tasks", JoinType.LEFT);
            return name == null ? cb.and() : cb.like(cb.lower(from.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    static PredicateSpecification<Project> specStatus(String status) {
        return (from, cb) -> {
            from.fetch("tasks", JoinType.LEFT);
            return status == null ? cb.and() : cb.like(cb.lower(from.get("status")), "%" + status.toLowerCase() + "%");
        };
    }

}
