package com.example.jwt.demojwt.entities.Request;

public class AppRequest {
    private String applicationName;

    public AppRequest() {
        applicationName = "";
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}