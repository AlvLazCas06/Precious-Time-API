package com.salesianostriana.dam.precioustime.user.dto;

public record LoginResponse(
        String token,
        String username
) {
}
