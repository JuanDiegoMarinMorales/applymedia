package com.example.landing.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.landing.dto.Token;
@Service
public class TokenService {
    

    public static Token cogerToken(final String token){

        String [] elements=token.split(",");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("landingSecret")).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(elements[0]);

        return Token.builder().clickId(jwt.getClaim("clickId").asString())
        .campaignId(jwt.getClaim("campaign").asInt())
        .country(jwt.getClaim("country").asString())
        .network(jwt.getClaim("network").asInt())
        .msisdn(jwt.getClaim("msisdn").asLong())
        .ip(jwt.getClaim("ip").asInt())
        .build();
    }
}
