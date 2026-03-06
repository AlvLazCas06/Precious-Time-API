package com.salesianostriana.dam.precioustime.category.validation.annotation;

import com.salesianostriana.dam.precioustime.category.validation.validator.UniqueCategoryNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCategoryNameValidator.class)
@Documented
public @interface UniqueCategoryName {

    String message() default "Ya existe una categoría con este nombre";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
