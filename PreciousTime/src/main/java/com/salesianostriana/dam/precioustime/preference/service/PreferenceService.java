package com.salesianostriana.dam.precioustime.preference.service;

import com.salesianostriana.dam.precioustime.preference.repository.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

}
