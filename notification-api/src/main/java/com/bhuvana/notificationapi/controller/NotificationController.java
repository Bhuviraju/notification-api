package com.bhuvana.notificationapi.controller;

import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.bhuvana.notificationapi.dto.NotificationResponse;
import com.bhuvana.notificationapi.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired(required = true)
    private NotificationService notificationService;

    @PostMapping
    public NotificationResponse sendNotification(@Valid @RequestBody NotificationRequest request) {
        return notificationService.ProcessNotification(request);
    }

}
