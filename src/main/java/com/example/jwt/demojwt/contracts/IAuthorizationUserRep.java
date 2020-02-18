package com.example.jwt.demojwt.contracts;

import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.entities.AuthorizationUser;

public interface IAuthorizationUserRep {
	UUID CreateAuthorizationUser(AuthorizationUser any);
	List<AuthorizationUser> GetAll();
	Boolean GetAuthorizationByUserAndApp(UUID userCode, UUID appCode);
}
