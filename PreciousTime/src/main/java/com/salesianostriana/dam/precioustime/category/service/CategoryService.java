package com.salesianostriana.dam.precioustime.category.service;

import com.salesianostriana.dam.precioustime.category.dto.CreateCategoryRequest;
import com.salesianostriana.dam.precioustime.category.dto.EditCategoryRequest;
import com.salesianostriana.dam.precioustime.category.exception.CategoryNotFoundException;
import com.salesianostriana.dam.precioustime.category.model.Category;
import com.salesianostriana.dam.precioustime.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category saveCategory(CreateCategoryRequest cmd) {
        return categoryRepository.save(cmd.toEntity());
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category editCategory(Long id, EditCategoryRequest cmd) {
        return categoryRepository.findById(id).map(category -> {
            category.setColor(cmd.color());
            category.setEmoji(cmd.emoji());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Page<Category> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        return categories;
    }

}
