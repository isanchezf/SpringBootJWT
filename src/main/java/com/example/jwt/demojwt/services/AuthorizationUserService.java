package com.example.jwt.demojwt.services;

import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IAuthorizationUserRep;
import com.example.jwt.demojwt.entities.AuthorizationUser;
import com.example.jwt.demojwt.entities.Request.AuthorizationUserRequest;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationUserService {

	@Qualifier("beanAuthorizationUserRep")
	IAuthorizationUserRep _authRep;

	@Autowired
	public AuthorizationUserService(IAuthorizationUserRep _authRep) {
		this._authRep = _authRep;
	}

	public UUID CreateAuthorizationUser(AuthorizationUserRequest request) {
		ValidateRequest(request);
		AuthorizationUser userAuth = new AuthorizationUser(
			request.getApplicationCode(),
			request.getUserCode()
		);

		return this._authRep.CreateAuthorizationUser(userAuth);
	}

	public List<AuthorizationUser> GetAll(){
		return _authRep.GetAll();
	}

	private void ValidateRequest(AuthorizationUserRequest request) {
		if (request == null || request.getUserCode() == null || request.getApplicationCode() == null) {
			throw new CustomExceptions.BadRequestException("Validar campos obligatorios");
		}
	}
}
