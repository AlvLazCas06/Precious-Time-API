package com.salesianostriana.dam.precioustime.user.dto;

import com.salesianostriana.dam.precioustime.user.model.User;

public record UserResponse(
        String username,
        String email,
        String fullName,
        String phoneNumber,
        boolean premium
) {

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.isPremium()
        );
    }

}
