package com.salesianostriana.dam.precioustime.reminder.repository;

import com.salesianostriana.dam.precioustime.reminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
