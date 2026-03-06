package com.salesianostriana.dam.precioustime.category.controller;

import com.salesianostriana.dam.precioustime.category.dto.CategoryResponse;
import com.salesianostriana.dam.precioustime.category.dto.CreateCategoryRequest;
import com.salesianostriana.dam.precioustime.category.dto.EditCategoryRequest;
import com.salesianostriana.dam.precioustime.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryResponse> getCategories(@PageableDefault Pageable pageable) {
        return categoryService.getAllCategories(pageable)
                .map(CategoryResponse::of);
    }

    @PostMapping("/admin")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest cmd) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryResponse.of(categoryService.saveCategory(cmd)));
    }

    @PutMapping("/admin/{id:[0-9]+}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable Long id, @Valid @RequestBody EditCategoryRequest cmd) {
        return ResponseEntity.ok(
                CategoryResponse.of(categoryService.editCategory(id, cmd))
        );
    }

    @GetMapping("/{id:[0-9]}")
    public CategoryResponse getCategory(@PathVariable Long id) {
        return CategoryResponse.of(categoryService.getById(id));
    }
}
