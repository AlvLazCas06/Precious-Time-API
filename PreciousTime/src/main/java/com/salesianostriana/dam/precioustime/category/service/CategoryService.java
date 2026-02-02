package com.salesianostriana.dam.precioustime.category.service;

import com.salesianostriana.dam.precioustime.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

}
