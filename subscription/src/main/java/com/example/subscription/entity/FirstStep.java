package com.example.subscription.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="firststep")
@Entity
@NoArgsConstructor // constructor por defecto
@AllArgsConstructor // constructor parametrizado
public class FirstStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "campaign_id")
    private String campaingnId;

    @Column(name = "click_id")
    private String clickId;

    @Column(name = "country_id")
    private String countryCode;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @Column(name = "network_id")
    private Integer network;

    @Column(name = "ip")
    private String ip;

    @Column(name = "msisdn")
    private Long msisdn;

    @Column(name = "pin")
    private Integer pin;
}
