package com.example.jwt.demojwt.entities.Response;

import java.util.UUID;

public class ApplicationResponse {
    private UUID applicationCode;
    private String applicationName;
    private String publicKey;
    

    public ApplicationResponse(String appName, UUID appCode, String pubKey) {
        applicationName = appName;
        applicationCode = appCode;
        publicKey = pubKey;
    }

    public UUID getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(UUID applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}