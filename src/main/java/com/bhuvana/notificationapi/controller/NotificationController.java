package com.bhuvana.notificationapi.controller;

import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.bhuvana.notificationapi.dto.NotificationResponse;
import com.bhuvana.notificationapi.entity.NotificationEntity;
import com.bhuvana.notificationapi.repository.NotificationRepository;
import com.bhuvana.notificationapi.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationService notificationService,
                                  NotificationRepository notificationRepository) {
        this.notificationService = notificationService;
        this.notificationRepository = notificationRepository;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(
            @Valid @RequestBody NotificationRequest request) {

        NotificationResponse response =
                notificationService.processNotification(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NotificationEntity>> getAllNotifications() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }
}