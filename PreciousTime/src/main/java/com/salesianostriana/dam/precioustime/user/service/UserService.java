package com.salesianostriana.dam.precioustime.user.service;

import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;
import com.salesianostriana.dam.precioustime.user.exception.UserNotFoundException;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el username: " + username));
    }

}
