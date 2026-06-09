package com.talenthub.gateway.utils;

public class GatewayConstraints {
    public static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    public static  final String HEADER_AUTHORIZATION = "Authorization";

    // JwtAuthFilter
    public static final String HEADER_USER_ID= "X-User-ID";
    public static final String HEADER_EMAIL= "X-User-Email";
    public static final String HEADER_ROLE_NAME= "X-User-Roles";


    public static final int ORDER_CORRELATION = -200;
    public static final int ORDER_LOGGING = -100;
    public static final int ORDER_JWT_AUTH = -90;

}
