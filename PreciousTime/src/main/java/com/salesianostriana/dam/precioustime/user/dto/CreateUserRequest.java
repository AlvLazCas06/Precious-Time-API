package com.salesianostriana.dam.precioustime.user.dto;

import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.validation.annotation.FieldsValueMatch;
import com.salesianostriana.dam.precioustime.user.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password", fieldMatch = "verifyPassword",
                message = "{createUserDto.password.noMatch}"
        ),
})
public record CreateUserRequest(
        @NotBlank
        @UniqueUsername
        String username,
        @NotBlank
        String password,
        @NotBlank
        String verifyPassword,
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        @Email
        String email
) {

    public User toEntity() {
        return User.builder()
                .username(username)
                .name(name)
                .lastname(lastname)
                .email(email)
                .build();
    }

}
