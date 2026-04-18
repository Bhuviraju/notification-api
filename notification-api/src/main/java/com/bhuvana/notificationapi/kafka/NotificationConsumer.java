package com.bhuvana.notificationapi.kafka;

import com.bhuvana.notificationapi.config.KafkaTopicConfig;
import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = KafkaTopicConfig.NOTIFICATION_TOPIC, groupId = "notification-group")
    public void consume(String message) {
        System.out.println("RAW MESSAGE RECEIVED: " + message);

        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            System.out.println("CONSUMED MESSAGE: " + request.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}