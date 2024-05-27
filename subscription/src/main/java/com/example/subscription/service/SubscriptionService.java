package com.example.subscription.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.subscription.dto.NotificationDTO;
import com.example.subscription.entity.FirstStep;
//import from idb
import com.example.subscription.entity.Subscription;
import com.example.subscription.repository.FirstStepRepository;
import com.example.subscription.repository.SubscriptionRepository;
import com.idb.kafka.services.StatsKafkaService;

import lombok.extern.slf4j.Slf4j;
/*
 * Service Class where we are going to have
 * our functions to handle the Costumer´s data 
 * and other kind of stuff
 */

@Slf4j
@Service
public class SubscriptionService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StatsKafkaService kafkaService;
	@Autowired
	private FirstStepRepository fs;
	@Autowired
	private SubscriptionRepository sr;

	public String handleSubcription(NotificationDTO notification) {
		String response = "ERROR";

		if (sr.findByMsisdn(notification.getMsisdn()).isEmpty()) {

			FirstStep f = fs.findByMsisdn(notification.getMsisdn()).get(0);
			Subscription subscription = new Subscription();

			subscription.setClickId(f.getClickId());
			subscription.setMsisdn(notification.getMsisdn());
			subscription.setCampaignId(Integer.parseInt(f.getCampaingnId()));
			subscription.setCountryId(Integer.valueOf(f.getCountryCode()));
			subscription.setActive(true);
			subscription.setState("ACTIVE");
			subscription.setNetworkId(f.getNetwork());
			subscription.setDetected(false);
			subscription.setSubscriptionDate(LocalDate.now());
			subscription.setSubscriptionTimestamp(LocalDateTime.now());
			subscription.setUnsubscriptionTimestamp(null);
			subscription.setFirstCharged(false);
			subscription.setBillingSuccess(null);
			subscription.setBillingFail(null);
			subscription.setLastDr(null);
			subscription.setLastDrSuccess(null);
			subscription.setDateFirstBilled(null);
			subscription.setLastLogin(LocalDateTime.now());

			sr.save(subscription);

			kafkaService.sendSubscription(subscription.getClickId(), subscription.getCampaignId(), subscription.getMsisdn(), subscription.getId().toString(), 67291, false);

			ResponseEntity<String> entity = restTemplate.getForEntity(
					"http://localhost:8082/login/" + notification.getMsisdn(), String.class);

			response = entity.getBody();
		}
		return response;
	}

	public String login(String msisdn) {

		String response = "ERROR";
		Boolean exist = sr.findByMsisdn(msisdn).isPresent();
		if (exist)
			response = "OK";

		return response;
	}
}
