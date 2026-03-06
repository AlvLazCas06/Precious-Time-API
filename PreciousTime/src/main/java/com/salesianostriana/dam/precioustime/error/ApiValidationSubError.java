package com.salesianostriana.dam.precioustime.error;

import lombok.Builder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Builder
public record ApiValidationSubError(
        String object,
        String message,
        String field,
        Object rejectedValue
) {

    public static ApiValidationSubError from(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            return ApiValidationSubError.builder()
                    .object(fieldError.getObjectName())
                    .message(fieldError.getDefaultMessage())
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue())
                    .build();
        } else {
            return ApiValidationSubError.builder()
                    .object(error.getObjectName())
                    .message(error.getDefaultMessage())
                    .build();
        }
    }

}
