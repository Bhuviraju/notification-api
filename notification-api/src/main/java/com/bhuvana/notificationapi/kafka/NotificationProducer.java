package com.bhuvana.notificationapi.kafka;

import com.bhuvana.notificationapi.config.KafkaTopicConfig;
import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(NotificationRequest request) {
        try {
            System.out.println("PRODUCER CALLED");
            String jsonMessage = objectMapper.writeValueAsString(request);
            kafkaTemplate.send(KafkaTopicConfig.NOTIFICATION_TOPIC, jsonMessage);
            System.out.println("SENT TO KAFKA: " + jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}