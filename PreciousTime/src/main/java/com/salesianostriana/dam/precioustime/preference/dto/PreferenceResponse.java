package com.salesianostriana.dam.precioustime.preference.dto;

import com.salesianostriana.dam.precioustime.preference.model.Preference;

public record PreferenceResponse(
        boolean notificationsActive,
        String theme,
        String type
) {

    public static PreferenceResponse of(Preference preference) {
        return new PreferenceResponse(
                preference.isNotificationsActive(),
                preference.getTheme().name().toLowerCase(),
                preference.getType().name().toLowerCase().replace("_", " ")
        );
    }

}
