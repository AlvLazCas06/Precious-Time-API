package com.salesianostriana.dam.precioustime.preference.repository;

import com.salesianostriana.dam.precioustime.preference.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    Optional<Preference> findByAuthor(String author);

}
