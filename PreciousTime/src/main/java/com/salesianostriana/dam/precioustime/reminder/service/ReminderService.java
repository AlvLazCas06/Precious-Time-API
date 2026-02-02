package com.salesianostriana.dam.precioustime.reminder.service;

import com.salesianostriana.dam.precioustime.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;

}
