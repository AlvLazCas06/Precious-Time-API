package com.salesianostriana.dam.precioustime.user.dto;

import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.model.UserRole;

import java.util.Set;

public record UserResponse(
        String username,
        String email,
        String fullName,
        boolean premium,
        Set<UserRole> roles
) {

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.isPremium(),
                user.getRoles()
        );
    }

}
