package com.stackfortech.springsecurityjwt.config;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 1000*60*60; // 1 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up/**";
    public static final String H2_CONSOLE = "/h2-console/**";

}
