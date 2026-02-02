package com.salesianostriana.dam.precioustime.reminder.dto;

import com.salesianostriana.dam.precioustime.reminder.model.Reminder;

public record ReminderResponse(
        Long id,
        String title,
        String message,
        boolean read
) {

    public static ReminderResponse of(Reminder reminder) {
        return new ReminderResponse(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getMessage(),
                reminder.isRead()
        );
    }

}
