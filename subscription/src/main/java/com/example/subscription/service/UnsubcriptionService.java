package com.example.subscription.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.subscription.dto.NotificationDTO;
import com.example.subscription.entity.Subscription;
import com.example.subscription.repository.SubscriptionRepository;
import com.idb.kafka.enums.SignoffType;
import com.idb.kafka.services.StatsKafkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.stereotype.Service
public class UnsubcriptionService {
    
    
	@Autowired
	private SubscriptionRepository sr;

	@Autowired
	private StatsKafkaService kafkaService;

	public void handleUnsubcription(NotificationDTO notification) {
		
		if(!sr.findByMsisdnStateActive(notification.getMsisdn()).isEmpty()){
			log.info("He encontrad FirstStep en unsub");

			
			Subscription subscription= sr.findByMsisdnStateActive(notification.getMsisdn()).get();
			subscription.setState("INACTIVE");
            subscription.setActive(false);
            subscription.setUnsubscriptionTimestamp(LocalDateTime.now());
			sr.save(subscription);

		}else{
			log.error("No se ha encontrado el usuario en la base de datos", new Throwable());
		}
		
	}
}
