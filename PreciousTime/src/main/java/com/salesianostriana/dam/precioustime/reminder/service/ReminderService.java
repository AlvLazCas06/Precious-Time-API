package com.salesianostriana.dam.precioustime.reminder.service;

import com.salesianostriana.dam.precioustime.email.dto.EmailDTO;
import com.salesianostriana.dam.precioustime.email.service.EmailService;
import com.salesianostriana.dam.precioustime.preference.model.NotificationType;
import com.salesianostriana.dam.precioustime.preference.model.Preference;
import com.salesianostriana.dam.precioustime.preference.service.PreferenceService;
import com.salesianostriana.dam.precioustime.reminder.dto.CreateReminderRequest;
import com.salesianostriana.dam.precioustime.reminder.exception.ReminderNotFoundException;
import com.salesianostriana.dam.precioustime.reminder.model.Reminder;
import com.salesianostriana.dam.precioustime.reminder.repository.ReminderRepository;
import com.salesianostriana.dam.precioustime.shared.exception.BadRequestException;
import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final PreferenceService preferenceService;
    private final UserService userService;
    private final EmailService emailService;

    public List<Reminder> findRemindersByUser(User user) {
        List<Reminder> reminders = reminderRepository.findByReceiver(user);
        if (reminders.isEmpty()) {
            throw new ReminderNotFoundException();
        }
        return reminders;
    }

    public Reminder sendReminder(CreateReminderRequest cmd) {
        Reminder reminder = cmd.toEntity();
        try {
            User user = userService.findByUsername(cmd.username());
            Preference preference = preferenceService.getPreferenceByAuthor(user.getUsername());
            if (!preference.isNotificationsActive()) {
                throw new BadRequestException("Este usuario tiene desactivadas sus notificaciones");
            }
            if (preference.getType().equals(NotificationType.EMAIL)) {
                emailService.sendMail(new EmailDTO(user.getEmail(), reminder.getTitle(), reminder.getMessage()));
            }
            reminder.setReceiver(user);
        } catch (NotFoundException | UsernameNotFoundException | BadRequestException | MessagingException e) {
            throw new BadRequestException(e.getMessage());
        }
        return reminderRepository.save(reminder);
    }

    public Reminder checkRead(Long id) {
        return reminderRepository.findById(id)
                .map(reminder -> {
                    reminder.setRead(true);
                    return reminderRepository.save(reminder);
                })
                .orElseThrow(() -> new ReminderNotFoundException(id));
    }

}
