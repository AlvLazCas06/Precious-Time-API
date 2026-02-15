package com.salesianostriana.dam.precioustime.user.controller;

import com.salesianostriana.dam.precioustime.security.auth.AuthService;
import com.salesianostriana.dam.precioustime.security.jwt.JwtAccessTokenService;
import com.salesianostriana.dam.precioustime.user.dto.CreateUserRequest;
import com.salesianostriana.dam.precioustime.user.dto.LoginRequest;
import com.salesianostriana.dam.precioustime.user.dto.LoginResponse;
import com.salesianostriana.dam.precioustime.user.dto.UserResponse;
import com.salesianostriana.dam.precioustime.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.doLogin(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest cmd) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(userService.createUser(cmd)));
    }

}
