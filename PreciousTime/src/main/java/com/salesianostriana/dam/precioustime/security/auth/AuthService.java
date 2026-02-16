package com.salesianostriana.dam.precioustime.security.auth;

import com.salesianostriana.dam.precioustime.security.jwt.JwtAccessTokenService;
import com.salesianostriana.dam.precioustime.user.dto.LoginRequest;
import com.salesianostriana.dam.precioustime.user.dto.LoginResponse;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    public LoginResponse doLogin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        User user = userRepository.findByUsername(authentication.getName()).get();
        String token = jwtAccessTokenService.generateAccessToken(user);
        return new LoginResponse(token, authentication.getName());
    }

}
