package com.example.subscription.service;

import com.example.subscription.dto.NotificationDTO;
import com.example.subscription.enums.ActionTypeStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Slf4j
@RequiredArgsConstructor
public class Service {
    
    private final ObjectMapper mapper;

    private final SubscriptionService subscriptionService;
    private final UnsubcriptionService unsub;
    private final BillingService billingService;

    public void handleCustomerState(String element) {

		try {
			NotificationDTO notification = mapper.readValue(element, NotificationDTO.class);
			
			if (notification.getActionType() == ActionTypeStatus.SUB_BILLING_NO.getNumber()){

				subscriptionService.handleSubcription(notification);

			}else if(notification.getActionType()==ActionTypeStatus.UNSUB.getNumber()){

				unsub.handleUnsubcription(notification);

			}else if(notification.getActionType()==ActionTypeStatus.SUB_BILLING_YES.getNumber()){
				
				billingService.handleBilling(notification);

			}else if(notification.getActionType()==ActionTypeStatus.BILLING_FAILED.getNumber()){

				billingService.handleBillingFail(notification);

			}

		} catch (Exception e) {

			log.error("Error parsing NotificationDTO {} caused by {}", element, e.getMessage());

		}
	}
}
