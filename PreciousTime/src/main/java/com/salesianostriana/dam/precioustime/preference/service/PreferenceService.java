package com.salesianostriana.dam.precioustime.preference.service;

import com.salesianostriana.dam.precioustime.preference.dto.EditPreferenceRequest;
import com.salesianostriana.dam.precioustime.preference.exception.PreferenceNotFoundException;
import com.salesianostriana.dam.precioustime.preference.model.Preference;
import com.salesianostriana.dam.precioustime.preference.repository.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public Preference savePreference() {
        return preferenceRepository.save(Preference.builder().build());
    }

    public Preference editPreference(Long id, EditPreferenceRequest cmd) {
        return preferenceRepository.findById(id)
                .map(preference -> {
                    preference.setType(cmd.type());
                    preference.setTheme(cmd.theme());
                    preference.setNotificationsActive(cmd.notificationsActive());
                    return preferenceRepository.save(preference);
                }).orElseThrow(() -> new PreferenceNotFoundException(id));
    }

    public Preference getPreferenceByAuthor(String author) {
        return preferenceRepository.findByAuthor(author)
                .orElseThrow(() -> new PreferenceNotFoundException("El autor %s, no tiene las preferencias creadas".formatted(author)));
    }

}
