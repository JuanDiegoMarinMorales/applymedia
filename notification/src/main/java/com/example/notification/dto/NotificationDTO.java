package com.example.notification.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationDTO {
    private String username;
    private String password;
    private String msisdn;
    private String shortcode;
    private Integer serviceId;
    private Integer spId;
    private Integer actionType;
    private LocalDateTime date;
    private Integer requestId;
}
