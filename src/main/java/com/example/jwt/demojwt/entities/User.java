package com.example.jwt.demojwt.entities;

import java.util.UUID;

public class User {
    private UUID userCode;
    private String userName;
    private String userPassword;

    public User(String userName, String userPass, UUID userCode) {
        this.setUserCode(userCode);
        this.setUserName(userName);
        this.setUserPassword(userPass);
    }

    public User() {
        this.setUserName("");
        this.setUserPassword("");
    }

    public UUID getUserCode() {
        return userCode;
    }

    public void setUserCode(UUID userCode) {
        this.userCode = userCode;
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
