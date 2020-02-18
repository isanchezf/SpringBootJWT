package com.example.jwt.demojwt.services;

import java.util.Optional;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IApplicationRep;
import com.example.jwt.demojwt.contracts.IAuthorizationUserRep;
import com.example.jwt.demojwt.contracts.IUserRep;
import com.example.jwt.demojwt.entities.Application;
import com.example.jwt.demojwt.entities.User;
import com.example.jwt.demojwt.entities.Request.AuthenticationRequest;
import com.example.jwt.demojwt.entities.Response.AuthenticationResponse;
import com.example.jwt.demojwt.utils.TokenGenerator;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Qualifier("benUserRep")
    IUserRep _userRep;
    @Qualifier("beanAuthorizationUserRep")
    IAuthorizationUserRep _authorizationRep;
    @Qualifier("beanApplicationRep")
    IApplicationRep _appRep;
    Optional<User> userData;

    @Autowired
    public AuthenticationService(IUserRep userRep, IAuthorizationUserRep authorizationRep, IApplicationRep appRep) {
        _userRep = userRep;
        _authorizationRep = authorizationRep;
        _appRep = appRep;
        userData = Optional.of(new User());
    }

    public AuthenticationResponse Authenticate(Optional<AuthenticationRequest> request)
            throws Exception {
        ValidateRequest(request);
        ValidateCredentials(request);
        ValidateAuthorization(request);
        return new AuthenticationResponse(GenerateToken(request.get().getApplicationCode()), "Bearer");
    }

    private void ValidateRequest(Optional<AuthenticationRequest> request) {
        if (!request.isPresent() || request.get().getUserName().isEmpty() || request.get().getUserPassword().isEmpty()
                || request.get().getApplicationCode() == null) {
            throw new CustomExceptions.BadRequestException("Por favor validar los campos obligatorios");
        }
    }

    private void ValidateCredentials(Optional<AuthenticationRequest> request) {
        userData = _userRep.GetUserByCredentials(request);
        if (!userData.isPresent() || userData.get().getUserCode() == null) {
            throw new CustomExceptions.NotFoundException(
                    "Credenciales ivalidas, por favor validar e intentar nuevamente");
        }
    }

    private void ValidateAuthorization(Optional<AuthenticationRequest> request) {
        Boolean authorize = _authorizationRep.GetAuthorizationByUserAndApp(userData.get().getUserCode(),
                request.get().getApplicationCode());
        if (!authorize) {
            throw new CustomExceptions.NotFoundException("Usuario no autorizado");
        }
    }

    private String GenerateToken(UUID appCode) throws Exception {
        Application app = _appRep.getByCode(appCode);
        return TokenGenerator.generateJwtToken(app.getPrivateKey());
    }
}
