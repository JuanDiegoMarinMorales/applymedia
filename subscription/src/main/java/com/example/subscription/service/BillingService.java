package com.example.subscription.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.subscription.dto.NotificationDTO;
import com.example.subscription.entity.FirstStep;
import com.example.subscription.entity.Subscription;
import com.example.subscription.enums.ActionTypeStatus;
import com.example.subscription.repository.FirstStepRepository;
import com.example.subscription.repository.SubscriptionRepository;
import com.idb.kafka.enums.BillingQuality;
import com.idb.kafka.enums.BillingStatus;
import com.idb.kafka.services.StatsKafkaService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BillingService {
    @Autowired
	private SubscriptionRepository sr;
	@Autowired
	private FirstStepRepository fs;
	@Autowired
	private StatsKafkaService kafkaService;

    public void handleBilling(NotificationDTO notification) {
		
		if (!sr.findByMsisdnStateActive(notification.getMsisdn()).isEmpty()) {
            
            log.info("He encontrado FirstStep en billing success");

			
			Subscription subscription= sr.findByMsisdn(notification.getMsisdn()).get();
			
			subscription.setLastDr(LocalDateTime.now());
			subscription.setFirstCharged(false);
            subscription.setLastDrSuccess(LocalDateTime.now());
            subscription.setBillingSuccess(subscription.getBillingSuccess()+1);
			sr.save(subscription);
			//kafkaService.sendBilling(subscription.getClickId(), subscription.getCampaignId(), subscription.getMsisdn(), subscription.getId().toString(), 60302, false, true, false, false, 0, BillingQuality.NORMAL, BillingStatus.SUCCESS, subscription.getSubscriptionTimestamp());

		}
	}

    public void handleBillingFail(NotificationDTO notification) {
		
		if (!sr.findByMsisdnStateActive(notification.getMsisdn()).isEmpty()) {
            
            log.info("He encontrad FirstStep en billing failed");

			
			Subscription subscription= sr.findByMsisdn(notification.getMsisdn()).get();
            subscription.setBillingFail(subscription.getBillingFail()+1);
			sr.save(subscription);


		}else{
			log.error("No se ha podido subscribir", new Throwable());
		}
	}
}
