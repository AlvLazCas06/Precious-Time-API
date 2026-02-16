package com.salesianostriana.dam.precioustime.preference.dto;

import com.salesianostriana.dam.precioustime.preference.model.NotificationType;
import com.salesianostriana.dam.precioustime.preference.model.Preference;
import com.salesianostriana.dam.precioustime.preference.model.Theme;
import jakarta.validation.constraints.NotNull;

public record EditPreferenceRequest(
        @NotNull
        boolean notificationsActive,
        @NotNull
        Theme theme,
        @NotNull
        NotificationType type
) {
}