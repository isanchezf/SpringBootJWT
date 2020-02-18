package com.example.jwt.demojwt.entities.Request;

import java.util.UUID;

public class AuthorizationUserRequest {
	private UUID userCode;
	private UUID applicationCode;

	public UUID getApplicationCode() {
		return applicationCode;
	}

	public UUID getUserCode() {
		return userCode;
	}

	public void setUserCode(UUID userCode) {
		this.userCode = userCode;
	}

	public void setApplicationCode(UUID applicationCode) {
		this.applicationCode = applicationCode;
	}
}
