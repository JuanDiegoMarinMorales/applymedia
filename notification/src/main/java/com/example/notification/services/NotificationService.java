package com.example.notification.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.notification.dto.NotificationDTO;
import com.example.notification.entities.Notification;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final RabbitTemplate rabbit;
    private final NotificationRepository servicio;
    private final ObjectMapper objectMapper;

    public String mensaje(

            String userName,
            String password,
            String msisdn,
            String shortCode,
            Integer serviceId,
            Integer spId,
            Integer actionType,
            String date,
            Integer requestId) {

        
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

            NotificationDTO notificationDTO = new NotificationDTO(userName, password, msisdn, shortCode, serviceId,
                    spId, actionType, dateTime, requestId);
            log.info("Recogida de datos:" + notificationDTO.toString());
            Notification notification = new Notification(notificationDTO);
            log.info("(ALG) Saving notification to DB: {}", notificationDTO);

            if(servicio.findByMsisdn(msisdn).isEmpty()){
                servicio.save(notification);
            }

            
            // funcion de rabbit que de momento no se lo que hace
            String json = objectMapper.writeValueAsString(notification);
            rabbit.convertAndSend(Constants.DR_QUEUE, json);
            return Constants.OK_RESPONSE;
        } catch (Exception e) {
            log.error("Error parsing Notification {} cause by {}",
                    e.getMessage());
            return Constants.ERROR_RESPONSE;
        }
    }
}
