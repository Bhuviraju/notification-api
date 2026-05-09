package com.bhuvana.notificationapi.service;

import com.bhuvana.notificationapi.dto.NotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(NotificationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getDestination());
        message.setSubject("Notification from Notification API");
        message.setText(request.getMessage());

        javaMailSender.send(message);
    }
}
