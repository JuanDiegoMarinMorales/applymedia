package com.example.landing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Token {
    
    private Integer campaignId;
    private String clickId;
    private String country;
    private Integer network;
    private  Integer ip;
    private Long msisdn;
    private Integer pin;
}
