package com.salesianostriana.dam.precioustime.preference.service;

import com.salesianostriana.dam.precioustime.preference.dto.EditPreferenceRequest;
import com.salesianostriana.dam.precioustime.preference.exception.PreferenceNotFoundException;
import com.salesianostriana.dam.precioustime.preference.model.Preference;
import com.salesianostriana.dam.precioustime.preference.repository.PreferenceRepository;
import com.salesianostriana.dam.precioustime.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public Preference savePreference() {
        return preferenceRepository.save(Preference.builder().build());
    }

    public Preference editPreference(User user, EditPreferenceRequest cmd) {
        return preferenceRepository.findByAuthor(user.getUsername())
                .map(preference -> {
                    preference.setType(cmd.type());
                    preference.setTheme(cmd.theme());
                    preference.setNotificationsActive(cmd.notificationsActive());
                    return preferenceRepository.save(preference);
                })
                .orElseThrow(() -> new PreferenceNotFoundException("Las preferencias del usuario %s, no existen".formatted(user.getUsername())));
    }

    public Preference getPreferenceByAuthor(String author) {
        return preferenceRepository.findByAuthor(author)
                .orElseThrow(() -> new PreferenceNotFoundException("El autor %s, no tiene las preferencias creadas".formatted(author)));
    }

}
