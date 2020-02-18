package com.example.jwt.demojwt.entities.Response;

import java.util.UUID;

public class UserResponse {
    private UUID userCode;
    private String userName;

    public UserResponse(UUID userCode, String userName) {
        this.setUserCode(userCode);
        this.setUserName(userName);
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
}