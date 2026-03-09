package com.salesianostriana.dam.precioustime.reminder.repository;

import com.salesianostriana.dam.precioustime.reminder.model.Reminder;
import com.salesianostriana.dam.precioustime.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByReceiver(User receiver);

}
