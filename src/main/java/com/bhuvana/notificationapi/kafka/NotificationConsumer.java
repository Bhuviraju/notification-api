package com.bhuvana.notificationapi.kafka;

import com.bhuvana.notificationapi.config.KafkaTopicConfig;
import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.bhuvana.notificationapi.repository.NotificationRepository;
import com.bhuvana.notificationapi.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
// This allows Spring Boot app to start in AWS WITHOUT Kafka
@ConditionalOnProperty(
        name = "KAFKA_ENABLED",
        havingValue = "true",
        matchIfMissing = true
)
public class NotificationConsumer {

    private static final Logger log=
            LoggerFactory.getLogger(NotificationConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationConsumer(NotificationRepository notificationRepository,EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @RetryableTopic(
            attempts = "3", // total attempts (1 original + 2 retries)
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltTopicSuffix = "-dlt"
    )

    @KafkaListener(
            topics = KafkaTopicConfig.NOTIFICATION_TOPIC,
            groupId = "notification-group")

    public void consume(String message) {
        log.info("RAW MESSAGE RECEIVED: " + message);

        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            log.info("Processing notification for user: {}", request.getUserId());
            log.info("Processing notification id: {}", request.getNotificationId());
            log.info("CONSUMED MESSAGE: " + request.getMessage());

            // Failure simulation (for retry + DLQ testing)
            if (request.getMessage() != null &&
                    request.getMessage().contains("FAIL")) {

                throw new RuntimeException("Simulated notification failure");
            }

            // Type-based processing
            switch (request.getType()) {

                case "EMAIL":
                    log.info("Sending EMAIL to {}", request.getDestination());
                    emailService.sendEmail(request);
                    break;

                case "SMS":
                    log.info("Sending SMS to {}", request.getDestination());
                    break;

                case "PUSH":
                    log.info("Sending PUSH notification to {}", request.getDestination());
                    break;

                default:
                    log.warn("Unknown notification type: {}", request.getType());
            }
            updateStatus(request.getNotificationId(), "SENT");

        } catch (Exception e) {
            log.error("Error while consuming message",e);
            throw new RuntimeException(e);
        }
    }

    @DltHandler
    public void handleDlt(String message) {
        log.error("Message moved to DLT: {}", message);

        try {
            NotificationRequest request =
                    objectMapper.readValue(message, NotificationRequest.class);

            log.info("DLT notification id: {}", request.getNotificationId());

            updateStatus(request.getNotificationId(), "FAILED");

        } catch (Exception e) {
            log.error("Error while handling DLT message", e);
        }
    }

    private void updateStatus(Long notificationId, String status) {
        if (notificationId == null) {
            log.warn("Notification ID is null, cannot update status");
            return;
        }

        notificationRepository.findById(notificationId)
                .ifPresent(notification -> {
                    notification.setStatus(status);
                    notification.setUpdatedAt(LocalDateTime.now());
                    notificationRepository.save(notification);
                    log.info("Notification status updated to {}", status);
                });
    }

}