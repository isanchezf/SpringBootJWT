package com.example.jwt.demojwt.entities;

public class Authorization {
    private String applicationCode;
    private String userCode;

    public String getApplicationCode() {
        return applicationCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }
    
}