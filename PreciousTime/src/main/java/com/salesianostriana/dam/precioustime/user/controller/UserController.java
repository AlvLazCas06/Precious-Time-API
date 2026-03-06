package com.salesianostriana.dam.precioustime.user.controller;

import com.salesianostriana.dam.precioustime.user.dto.UserResponse;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponse getUser(@AuthenticationPrincipal User user) {
        return UserResponse.of(user);
    }

    @GetMapping("/admin")
    public Page<UserResponse> getUsers(@PageableDefault Pageable pageable) {
        return userService.getUsers(pageable)
                .map(UserResponse::of);
    }

}
