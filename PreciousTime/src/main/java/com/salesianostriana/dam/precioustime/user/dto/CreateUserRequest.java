package com.salesianostriana.dam.precioustime.user.dto;

public record CreateUserRequest(
        String username,
        String password,
        String verifyPassword,
        String avatar,
        String fullName
) {
}
