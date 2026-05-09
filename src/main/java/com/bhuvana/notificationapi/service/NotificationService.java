package com.bhuvana.notificationapi.service;

import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.bhuvana.notificationapi.dto.NotificationResponse;
import com.bhuvana.notificationapi.entity.NotificationEntity;
import com.bhuvana.notificationapi.kafka.NotificationProducer;
import com.bhuvana.notificationapi.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private static final Logger log=
            LoggerFactory.getLogger(NotificationProducer.class);

    private final NotificationProducer notificationProducer;
    private final NotificationRepository notificationRepository;

    @Value("${kafka.enabled:true}")
    private boolean kafkaEnabled;
    //constructor injection
    public NotificationService(NotificationProducer notificationProducer,
                               NotificationRepository notificationRepository) {
        this.notificationProducer = notificationProducer;
        this.notificationRepository=notificationRepository;
    }

    public NotificationResponse processNotification(NotificationRequest request){
        log.info("SERVICE CALLED");
        // save as pending
        NotificationEntity notification = new NotificationEntity();
        notification.setUserId(request.getUserId());
        notification.setType(request.getType());
        notification.setMessage(request.getMessage());
        notification.setDestination(request.getDestination());
        notification.setStatus("PENDING");
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        NotificationEntity saved = notificationRepository.save(notification);

        // Set ID in request
        request.setNotificationId(saved.getId());

        if (kafkaEnabled) {
            notificationProducer.send(request);
        } else {
            saved.setStatus("SENT");
            saved.setUpdatedAt(LocalDateTime.now());
            notificationRepository.save(saved);
        }
        //Send to Kafka
        //notificationProducer.send(request);

        return new NotificationResponse(
               "Notification received successfully",
               request.getUserId()
       );
    }
}
