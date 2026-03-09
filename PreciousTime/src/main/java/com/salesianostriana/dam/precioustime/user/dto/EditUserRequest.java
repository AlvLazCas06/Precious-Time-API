package com.salesianostriana.dam.precioustime.user.dto;

public record EditUserRequest(
        String name,
        String lastname,
        String email
) {
}
