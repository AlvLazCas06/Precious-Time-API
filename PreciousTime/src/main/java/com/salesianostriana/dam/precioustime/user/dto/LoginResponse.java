package com.salesianostriana.dam.precioustime.user.dto;

import com.salesianostriana.dam.precioustime.user.model.UserRole;

import java.util.Set;

public record LoginResponse(
        String token,
        String username,
        Set<UserRole> roles
) {
}
