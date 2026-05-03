package com.bhuvana.notificationapi.kafka;

import com.bhuvana.notificationapi.config.KafkaTopicConfig;
import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private static final Logger log=
           LoggerFactory.getLogger(NotificationProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
   public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate){
       this.kafkaTemplate= kafkaTemplate;
    }
    public void send(NotificationRequest request) {
        try {
            log.info("PRODUCER CALLED");
            String jsonMessage = objectMapper.writeValueAsString(request);
            kafkaTemplate.send(KafkaTopicConfig.NOTIFICATION_TOPIC, jsonMessage);
            log.info("SENT TO KAFKA: " + jsonMessage);
        } catch (Exception e) {
            log.error("Error sending message", e);
        }
    }
}