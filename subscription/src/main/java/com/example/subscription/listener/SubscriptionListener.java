package com.example.subscription.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.subscription.service.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
@RabbitListener(queues = "prueba2")
public class SubscriptionListener {

    private final Service service;
	
	@Bean
	public Queue NotificationConsumer() {

		final Map<String, Object> p = new HashMap<>();
		p.put("x-max-priority", 10);
		return new Queue("prueba2", true, false, false, p);
	}
    
	@RabbitHandler
	public void onMessage(@Payload final String element) {
		//Aqui recibimos el mensaje de rabbit que basicamente es el json
		//Con la funcion handlerCustomerState comprobamos que tipo de mensaje hemos obtenido
		try {
			log.info("NotificationDTO received: {}", element);
			service.handleCustomerState(element);
		} catch (final Exception e) {			
			log.error("Error {} handling NotificationDTO: {}", e.getMessage(), element);
		}
	}
}
