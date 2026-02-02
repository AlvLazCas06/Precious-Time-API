package com.salesianostriana.dam.precioustime.reminder.controller;

import com.salesianostriana.dam.precioustime.reminder.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

}
