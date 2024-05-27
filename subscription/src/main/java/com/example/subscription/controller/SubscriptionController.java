package com.example.subscription.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.example.subscription.service.SubscriptionService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {
    
    private final SubscriptionService service;
    @GetMapping("/login/{msisdn}")
    public String logueo(
        @PathVariable(value = "msisdn") String msisdn) {

        return service.login(msisdn);
    }
}
