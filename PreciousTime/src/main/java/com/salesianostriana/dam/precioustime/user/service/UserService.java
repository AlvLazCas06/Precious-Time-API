package com.salesianostriana.dam.precioustime.user.service;

import com.salesianostriana.dam.precioustime.user.dto.CreateUserRequest;
import com.salesianostriana.dam.precioustime.user.exception.UserNotFoundException;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el username: " + username));
    }

    public User createUser(CreateUserRequest cmd) {
        User user = cmd.toEntity();
        user.setPassword(passwordEncoder.encode(cmd.password()));
        return userRepository.save(user);
    }

}
