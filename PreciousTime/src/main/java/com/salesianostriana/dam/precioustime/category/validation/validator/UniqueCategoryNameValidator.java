package com.salesianostriana.dam.precioustime.category.validation.validator;

import com.salesianostriana.dam.precioustime.category.exception.CategoryDuplicatedException;
import com.salesianostriana.dam.precioustime.category.repository.CategoryRepository;
import com.salesianostriana.dam.precioustime.category.validation.annotation.UniqueCategoryName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryRepository.existsByName(name)) {
            throw new CategoryDuplicatedException();
        }
        return true;
    }

}
