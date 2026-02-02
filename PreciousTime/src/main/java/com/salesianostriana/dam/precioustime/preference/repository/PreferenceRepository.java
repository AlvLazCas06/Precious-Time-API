package com.salesianostriana.dam.precioustime.preference.repository;

import com.salesianostriana.dam.precioustime.preference.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
}
