package com.salesianostriana.dam.precioustime.reminder.dto;

import com.salesianostriana.dam.precioustime.reminder.model.Reminder;

import java.time.LocalDate;

public record ReminderResponse(
        Long id,
        String title,
        String message,
        boolean read,
        LocalDate sendAt
) {

    public static ReminderResponse of(Reminder reminder) {
        return new ReminderResponse(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getMessage(),
                reminder.isRead(),
                reminder.getSendAt()
        );
    }

}
