package com.group17.inventoryease.dtos;


// Source: https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac

public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse (String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}