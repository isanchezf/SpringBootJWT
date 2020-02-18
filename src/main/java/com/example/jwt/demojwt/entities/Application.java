package com.example.jwt.demojwt.entities;

import java.util.UUID;

public class Application {
    private UUID applicationCode;
    private String applicationName;
    private String publicKey;
    private String privateKey;

    public Application() {
        applicationName = "";
        publicKey = "";
        privateKey = "";
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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}