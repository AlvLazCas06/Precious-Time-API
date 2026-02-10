package com.salesianostriana.dam.precioustime.user.dto;

import com.salesianostriana.dam.precioustime.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String verifyPassword,
        @NotBlank
        String avatar,
        @NotBlank
        String fullName,
        @Email
        @NotBlank
        String email
) {

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .email(email)
                .build();
    }

}
