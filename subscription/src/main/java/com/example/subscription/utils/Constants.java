package com.example.subscription.utils;

public class Constants {

    public static final String DR_QUEUE = "prueba2.dz.notification";
    public static final String AF_QUEUE = "enkudo.afg.notification";
    public static final String OK_RESPONSE = "OK";
    public static final String ERROR_RESPONSE = "ERROR";
    
    public static final String URL_AUTH ="https://dz-dj.telenity.com/oauth/token?grant_type=client_credentials";
    public static final String URL_AUTH_AFG = "https://awcc-af.telenity.com/oauth/token?grant_type=client_credentials";
    
public static final String URL_MT_AFG = "https://awcc-af.telenity.com/awcc/gw/messaging/v1/outbound";
	
	public static final String URL_MT_ALG = "https://dz-dj.telenity.com/djezzy/gw/messaging/v1/outbound";
	//////////// PRIORITIES
	public static final Integer MEDIUM_PRIORITY = 5;

	public static final Integer HIGH_PRIORITY = 10;
	
	public static final String notifUrl = "http://enkudo.d1b.pw/notification/dz";
}
