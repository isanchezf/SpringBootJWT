package com.example.jwt.demojwt.entities.Request;

public class UserRequest {

    private String userName;
    private String userPassword;

    public UserRequest() {
        userName = "";
        userPassword = "";
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
