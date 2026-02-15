package com.salesianostriana.dam.precioustime.user.validation.validation;

import com.salesianostriana.dam.precioustime.user.repository.UserRepository;
import com.salesianostriana.dam.precioustime.user.validation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(username) && !userRepository.existsByUsername(username);
    }

}
