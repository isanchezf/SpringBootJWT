package com.example.jwt.demojwt.entities.Request;

import java.util.UUID;

public class AuthenticationRequest {
    private String userName;
    private String userPassword;
    private UUID applicationCode;

    public AuthenticationRequest(String userName, String userPassword, UUID appCode) {
        this.setUserName(userName);
        this.setUserPassword(userPassword);
        this.setApplicationCode(appCode);
    }

    public UUID getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(UUID applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
