package com.example.demo.restdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTTokenDTO {

    private String idToken;

    private String refreshToken;

    public JWTTokenDTO(String idToken, String refreshToken) {
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    @JsonProperty("id_refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

}
