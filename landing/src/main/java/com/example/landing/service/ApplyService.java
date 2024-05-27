package com.example.landing.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.landing.dto.Token;
import com.example.landing.entity.FirstStep;
import com.example.landing.repository.FirstStepRepository;
import com.example.landing.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.idb.click.model.ClickDto;
import com.idb.click.service.ClickService;
import com.idb.kafka.services.StatsKafkaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyService {

    private final RestTemplate restTemplate;
    private final FirstStepRepository servicio;
    private final ClickService clickService;
    private final StatsKafkaService kafkaService;
    private final TokenService tokenService;

    public String conexion(HttpServletRequest request,
            Model model,
            HttpSession sesion,
            String pagePath,
            String country,
            Long cid) {

        ClickDto clickDto = clickService.clickHandler(request, String.valueOf(cid));

        if (!clickDto.getIsResiduary().isEmpty()) {
            return "redirect:" + clickDto.getIsResiduary();
        }
        String token = JWT.create().withClaim("clickId", clickDto.getCorrelationId())
                .withClaim("campaign", clickDto.getCampaignId())
                .withClaim("network", clickDto.getNetworkId())
                .withClaim("country", clickDto.getCountryId())
                .withClaim("content", clickDto.getContentUrl())
                .withClaim("ip", clickDto.getIp())
                .withIssuer("auth0")
                .sign(Algorithm.HMAC256("landingSecret"));

        sesion.setAttribute("token", token);

        model.addAttribute("action", "/" + pagePath + "/msisdn");
        model.addAttribute("serviceId", Constants.SERVICEID);
        model.addAttribute("spId", Constants.SPID);
        model.addAttribute("shortcode", Constants.SHORT_CODE);
        model.addAttribute("uuid", UUID.randomUUID().toString());
        model.addAttribute("ts", System.currentTimeMillis());
        model.addAttribute("servicename", Constants.SERVICENAME);
        model.addAttribute("merchantname", Constants.MERCHANT_NAME);
        model.addAttribute("cid", cid);
        model.addAttribute("token", token);

        return "/" + pagePath + "/prepage";

    }

    public String msisdn(
            HttpServletRequest request,
            Model model,
            HttpSession sesion,
            String pagePath,
            Long msisdn,
            Long spId,
            String shortCode) {

        model.addAttribute("action", "/" + pagePath + "/deteccion");
        model.addAttribute("spId", spId);
        model.addAttribute("shortcode", shortCode);
        return "/" + pagePath + "/enter_msisdn";
    }

    public String deteccion(
            Model model,
            HttpSession sesion,
            String pagePath,
            String msisdn,
            Long spId,
            String shortCode) {

        Token token = new Token();
        String result = "";
        if (!sesion.getAttribute("token").toString().equals("")) {
            result = sesion.getAttribute("token").toString();
            token = tokenService.cogerToken(result);
        }

        FirstStep fs = new FirstStep();
        fs.setCampaingnId(String.valueOf(token.getCampaignId()));
        fs.setClickId(token.getClickId());
        fs.setCountryCode(token.getCountry());
        fs.setTimeStamp(LocalDateTime.now());
        fs.setIp((String.valueOf(token.getIp())));
        fs.setNetwork(token.getNetwork());

        Boolean exist;
        try {
            exist = servicio.findByMsisdn(Long.valueOf(msisdn)).isPresent();
        } catch (Exception e) {
            exist = false;
        }

        if (msisdn.isEmpty() || !isNumeric(msisdn) || msisdn.length() < 13 || exist) {

            model.addAttribute("action", "/" + pagePath + "/deteccion");
            model.addAttribute("spId", spId);
            model.addAttribute("shortcode", shortCode);
            model.addAttribute("error", "Invalid number");
            return "/" + pagePath + "/enter_msisdn";
        } else {

            fs.setMsisdn(Long.valueOf(msisdn));

            Map<String, String> requestContent = new HashMap<>();

            requestContent.put("user", Constants.USER_NAME);
            requestContent.put("password", Constants.PASSWORD);
            requestContent.put("msisdn", msisdn);
            requestContent.put("shortcode", shortCode);
            requestContent.put("serviceId", Constants.SERVICEID.toString());
            requestContent.put("spId", spId.toString());

            ObjectMapper mapper = new ObjectMapper();
            String jsonData = "";
            try {
                jsonData = mapper.writeValueAsString(requestContent);
            } catch (Exception e) {
                log.error("Error:" + e.getMessage());
                e.printStackTrace();
            }

            // Configurar los encabezados
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Crear la entidad HTTP
            HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);
            try {
                //Send pin to user for put on the input 
                ResponseEntity<String> response = restTemplate.postForEntity(Constants.HOST + "/sendSubPincode", entity,String.class);
                log.error("Response {}:" + response.getBody());
            } catch (Exception e) {
                log.error("Error {}:" + e.getMessage());

                int pin = (int) (Math.random() * 90000) + 10000;
                fs.setPin(Integer.valueOf(pin));
            }

            servicio.save(fs);

            model.addAttribute("action", "/" + pagePath + "/comprobarPin");
            return "/" + pagePath + "/pin";
        }
    }

    public String pin(
            HttpServletRequest request,
            Model model,
            HttpSession sesion,
            String pagePath,
            Integer pin) {

        if (isNumeric(String.valueOf(pin)) && String.valueOf(pin).length() == 5) {

            Token token = new Token();
            String result = "";

            if (!sesion.getAttribute("token").toString().equals("")) {
                result = sesion.getAttribute("token").toString();
                token = tokenService.cogerToken(result);
            }

            FirstStep fs = servicio.findByClickId(token.getClickId()).get();

            Map<String, String> requestContent = new HashMap<>();

            requestContent.put("user", Constants.USER_NAME);
            requestContent.put("password", Constants.PASSWORD);
            requestContent.put("msisdn", fs.getMsisdn().toString());
            requestContent.put("shortcode", Constants.SHORT_CODE);
            requestContent.put("serviceId", Constants.SERVICEID.toString());
            requestContent.put("spId", Constants.SPID.toString());
            requestContent.put("pincode", pin.toString());

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
                ResponseEntity<String> response = restTemplate.postForEntity(Constants.HOST + "/verifySubPincode",entity,String.class);
                log.error("Response {}:" + response.getBody());

            } catch (Exception e) {

                log.error("Error {}:" + e.getMessage());

            }

            if (servicio.findByClickId(token.getClickId()).isPresent()) {

                fs = servicio.findByClickId(token.getClickId()).get();
                int pinAComprobar = fs.getPin();

                if (pinAComprobar == pin) {
                    return "/" + pagePath + "/thankyou";
                }

            }

        }
        model.addAttribute("error", "Error pin");
        return "/" + pagePath + "/pin";
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Long.parseLong(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}