package com.salesianostriana.dam.precioustime.user.controller;

import com.salesianostriana.dam.precioustime.user.dto.UserResponse;
import com.salesianostriana.dam.precioustime.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public UserResponse getUser(@AuthenticationPrincipal User user) {
        return UserResponse.of(user);
    }

}
