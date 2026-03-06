package com.salesianostriana.dam.precioustime.reminder.controller;

import com.salesianostriana.dam.precioustime.reminder.dto.CreateReminderRequest;
import com.salesianostriana.dam.precioustime.reminder.dto.ReminderResponse;
import com.salesianostriana.dam.precioustime.reminder.service.ReminderService;
import com.salesianostriana.dam.precioustime.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping
    public Page<ReminderResponse> findReminderByReceiver(
            @PageableDefault Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        return reminderService.findRemindersByUser(user, pageable)
                .map(ReminderResponse::of);
    }

    @PostMapping("/admin")
    public ResponseEntity<ReminderResponse> sendReminder(@RequestBody CreateReminderRequest cmd) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReminderResponse.of(reminderService.sendReminder(cmd)));
    }

    @PatchMapping("/{id}")
    public ReminderResponse checkRead(@PathVariable Long id) {
        return ReminderResponse.of(reminderService.checkRead(id));
    }

}
