package com.example.subscription.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.example.subscription.service.SubscriptionService;
import com.example.subscription.service.UnsubcriptionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {
    
    private final SubscriptionService subscriptionService;
    private final UnsubcriptionService unsubcriptionService;
    @GetMapping("/login/{msisdn}")
    public String logueo(
        @PathVariable(value = "msisdn") String msisdn) {

        return subscriptionService.login(msisdn);
    }

    @GetMapping("/unsub/{msisdn}")
    public String unsub(
        @PathVariable(value = "msisdn") String msisdn) {

        return unsubcriptionService.unsub(msisdn);
    }
}
