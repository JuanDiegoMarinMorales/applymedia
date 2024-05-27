package com.example.subscription.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.subscription.dto.NotificationDTO;
import com.example.subscription.entity.Subscription;
import com.example.subscription.repository.SubscriptionRepository;
import com.example.subscription.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idb.kafka.enums.SignoffType;
import com.idb.kafka.services.StatsKafkaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnsubcriptionService {
    
    
	private final RestTemplate restTemplate;
	private final SubscriptionRepository sr;
	private StatsKafkaService kafkaService;

	public void handleUnsubcription(NotificationDTO notification) {
		
		if(!sr.findByMsisdnStateActive(notification.getMsisdn()).isEmpty()){

			Subscription subscription= sr.findByMsisdnStateActive(notification.getMsisdn()).get();
			subscription.setState("INACTIVE");
            subscription.setActive(false);
            subscription.setUnsubscriptionTimestamp(LocalDateTime.now());
			sr.save(subscription);

            Boolean sameDay = (notification.getDate().getDayOfMonth()==subscription.getSubscriptionDate().getDayOfMonth())? true : false;
            kafkaService.sendUnsubscription(subscription.getClickId(), subscription.getCampaignId(), subscription.getMsisdn(), subscription.getId().toString(), 1234, false, false, sameDay, null);

			//For provider unsub
			Map<String, String> requestContent = new HashMap<>();

            requestContent.put("user", Constants.USER_NAME);
            requestContent.put("password", Constants.PASSWORD);
            requestContent.put("msisdn", notification.getMsisdn().toString());
            requestContent.put("shortcode", Constants.SHORT_CODE);
            requestContent.put("serviceId", Constants.SERVICEID.toString());
            requestContent.put("spId", Constants.SPID.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            String jsonData = "";

            try {
                jsonData = mapper.writeValueAsString(requestContent);
            } catch (Exception e) {
                log.error("Error:" + e.getMessage());
                e.printStackTrace();
            }

            HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);

            try {
                //Call for verify pincode previusly sent to user 
                ResponseEntity<String> response = restTemplate.postForEntity(Constants.HOST + "/unsubscribeUser",entity,String.class);
                log.error("Response {}:" + response.getBody());

            } catch (Exception e) {
                log.error("Error {}:" + e.getMessage());
            }

		}else{
			log.error("Unsubcription failed", new Throwable());
		}
		
	}
}
