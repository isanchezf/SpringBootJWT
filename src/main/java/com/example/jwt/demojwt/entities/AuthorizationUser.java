package com.example.jwt.demojwt.entities;

import java.util.UUID;

public class AuthorizationUser {
    private UUID authorizationCode;
    private UUID applicationCode;
    private UUID userCode;

    public AuthorizationUser(UUID applicationCode, UUID userCode) {
        this.setApplicationCode(applicationCode);
        this.setUserCode(userCode);
        this.setAuthorizationCode(UUID.randomUUID());
    }

    public UUID getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(UUID authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public UUID getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(UUID applicationCode) {
        this.applicationCode = applicationCode;
    }

    public UUID getUserCode() {
        return userCode;
    }

    public void setUserCode(UUID userCode) {
        this.userCode = userCode;
    }
}
