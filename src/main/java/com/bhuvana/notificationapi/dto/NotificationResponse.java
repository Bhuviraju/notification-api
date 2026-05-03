package com.bhuvana.notificationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationResponse {

    private String message;
    private String userId;


}
