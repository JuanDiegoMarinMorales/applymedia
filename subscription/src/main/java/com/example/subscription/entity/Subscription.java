package com.example.subscription.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.subscription.dto.NotificationDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Our Entity that is match with our Subscription
 * table in the database
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "click_id")
	private String clickId;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "campaign_id")
	private Integer campaignId;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "active")
	private boolean active;

	@Column(name = "state")
	private String state;

	@Column(name = "network_id")
	private Integer networkId;

	@Column(name = "detected")
	private boolean detected;


	@Column(name = "subscription_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate subscriptionDate;

	@Column(name = "subscription_timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime subscriptionTimestamp;

	@Column(name = "unsubscription_timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime unsubscriptionTimestamp;

	@Column(name = "first_charged")
	private boolean firstCharged;

	@Column(name = "billing_success")
	private Integer billingSuccess;

	@Column(name = "billing_fail")
	private Integer billingFail;

	@Column(name = "last_dr")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastDr;

	@Column(name = "last_dr_success")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastDrSuccess;

	@Column(name = "date_first_billed")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFirstBilled;

	@Column(name = "last_login")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLogin;

	// Create a subscription entity from NotificationDTO
	// and from the FirstStep
	// I need to clarify wich data to save on some columns
	

		// subscription via SMS
	
}
