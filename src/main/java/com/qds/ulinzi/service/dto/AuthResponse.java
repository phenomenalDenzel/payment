package com.qds.ulinzi.service.dto;

public class AuthResponse {
    private String accessToken;
    private long expiresIn;
    private String refreshToken;

    public static AuthResponse asBuilder(){
        return new AuthResponse();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public AuthResponse expiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public AuthResponse refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
