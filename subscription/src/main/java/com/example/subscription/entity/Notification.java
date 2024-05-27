package com.example.subscription.entity;

import java.time.LocalDateTime;

import com.example.subscription.dto.NotificationDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String msisdn;
    private String shortCode;
    private Integer serviceId;
    private Integer spId;
    private Integer actionType;
    private LocalDateTime date;
    private Integer requestId;

    public Notification(NotificationDTO notification) {

        this.userName=notification.getUsername();
        this.password=notification.getPassword();
        this.msisdn=notification.getMsisdn();
        this.shortCode=notification.getShortcode();
        this.serviceId=notification.getServiceId();
        this.spId=notification.getSpId();
        this.actionType=notification.getActionType();
        this.date=notification.getDate();
        this.requestId=notification.getRequestId();
    }
}
