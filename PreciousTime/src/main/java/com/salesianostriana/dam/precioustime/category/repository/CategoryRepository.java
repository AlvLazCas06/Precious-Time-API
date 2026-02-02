package com.salesianostriana.dam.precioustime.category.repository;

import com.salesianostriana.dam.precioustime.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
