package com.example.notification.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.notification.services.NotificationService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class NotificationController {

    private final NotificationService servicio;

    @GetMapping("/datasync")
    public String landing(
            @RequestParam(value = "username", required = true, defaultValue = "") String userName,
            @RequestParam(value = "password", required = true, defaultValue = "") String password,
            @RequestParam(value = "msisdn", required = true, defaultValue = "") String msisdn,
            @RequestParam(value = "shortcode", required = true, defaultValue = "") String shortCode,
            @RequestParam(value = "serviceId", required = true, defaultValue = "") Integer serviceId,
            @RequestParam(value = "spId", required = true, defaultValue = "") Integer spId,
            @RequestParam(value = "actionType", required = true, defaultValue = "") Integer actionType,
            @RequestParam(value = "date", required = true, defaultValue = "") String date,
            @RequestParam(value = "requestId", required = true, defaultValue = "") Integer requestId
            ) {

        return servicio.mensaje(userName,password,msisdn,shortCode,serviceId,spId,actionType,date,requestId);
    }

}
