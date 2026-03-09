package com.salesianostriana.dam.precioustime.user.dto;

import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.model.UserRole;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
        String username,
        String email,
        String name,
        String lastname,
        boolean premium,
        Set<UserRole> roles,
        boolean active,
        LocalDateTime registerAt
) {

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getLastname(),
                user.isPremium(),
                user.getRoles(),
                user.isActive(),
                user.getRegisterAt()
        );
    }

}
