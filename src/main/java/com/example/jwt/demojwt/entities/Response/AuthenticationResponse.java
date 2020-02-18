package com.example.jwt.demojwt.entities.Response;

public class AuthenticationResponse {
    private String token;
    private String type;

    public AuthenticationResponse(String token, String type) {
        this.setToken(token);
        this.setType(type);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }    
}