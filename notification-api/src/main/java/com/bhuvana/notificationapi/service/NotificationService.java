package com.bhuvana.notificationapi.service;

import com.bhuvana.notificationapi.dto.NotificationRequest;
import com.bhuvana.notificationapi.dto.NotificationResponse;
import com.bhuvana.notificationapi.kafka.NotificationProducer;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public NotificationResponse ProcessNotification(NotificationRequest request){
        System.out.println("SERVICE CALLED");
        notificationProducer.send(request);
        return new NotificationResponse(
               "Notification received successfully",
               request.getUserId()
       );
    }
}
