package com.salesianostriana.dam.precioustime.preference.controller;

import com.salesianostriana.dam.precioustime.preference.dto.EditPreferenceRequest;
import com.salesianostriana.dam.precioustime.preference.dto.PreferenceResponse;
import com.salesianostriana.dam.precioustime.preference.service.PreferenceService;
import com.salesianostriana.dam.precioustime.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/preferences")
@RequiredArgsConstructor
public class PreferenceController {

    private final PreferenceService preferenceService;

    @GetMapping
    public ResponseEntity<PreferenceResponse> getUserPreference(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                PreferenceResponse.of(preferenceService.getPreferenceByAuthor(user.getUsername()))
        );
    }

    @PostMapping
    public ResponseEntity<PreferenceResponse> createPreference() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PreferenceResponse.of(preferenceService.savePreference()));
    }

    @PutMapping
    public ResponseEntity<PreferenceResponse> editPreference(@AuthenticationPrincipal User user, @Valid @RequestBody EditPreferenceRequest cmd) {
        return ResponseEntity.ok(
                PreferenceResponse.of(preferenceService.editPreference(user, cmd))
        );
    }

}
