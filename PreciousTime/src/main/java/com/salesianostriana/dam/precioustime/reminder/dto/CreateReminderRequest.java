package com.salesianostriana.dam.precioustime.reminder.dto;

import com.salesianostriana.dam.precioustime.reminder.model.Reminder;

public record CreateReminderRequest(
        String title,
        String message,
        String username
) {

    public Reminder toEntity() {
        return Reminder.builder()
                .title(title)
                .message(message)
                .build();
    }

}
