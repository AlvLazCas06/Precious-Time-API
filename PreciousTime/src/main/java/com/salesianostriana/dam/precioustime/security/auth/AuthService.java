package com.salesianostriana.dam.precioustime.security.auth;

import com.salesianostriana.dam.precioustime.security.jwt.JwtAccessTokenService;
import com.salesianostriana.dam.precioustime.user.dto.LoginRequest;
import com.salesianostriana.dam.precioustime.user.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenService jwtAccessTokenService;

    public LoginResponse doLogin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String token = jwtAccessTokenService.generateAccessToken(authentication.getName());
        return new LoginResponse(token, authentication.getName());
    }

}
