package com.bhuvana.notificationapi.dto;

import jakarta.validation.constraints.NotBlank;

public class NotificationRequest {

    @NotBlank(message = "userId is required")
    private String userId;
    @NotBlank(message = "type is required")
    private String type; //SMS,EMAIL,PUSH
    @NotBlank(message = "message is required")
    private String message;
    @NotBlank(message = "destination is required")
    private String destination;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
