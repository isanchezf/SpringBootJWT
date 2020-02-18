package com.example.jwt.demojwt.entities.Response;

import java.util.UUID;

public class CreateAppResponse {
    private UUID applicationCode;
    private String publicKey;

    public CreateAppResponse(UUID appCode, String pubKey) {
        applicationCode = appCode;
        publicKey = pubKey;
    }

    public UUID getApplicationCode() {
        return applicationCode;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setApplicationCode(UUID applicationCode) {
        this.applicationCode = applicationCode;
    }
}