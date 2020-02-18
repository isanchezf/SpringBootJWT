package com.example.jwt.demojwt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IApplicationRep;
import com.example.jwt.demojwt.entities.Application;
import com.example.jwt.demojwt.entities.Request.AppRequest;
import com.example.jwt.demojwt.entities.Response.ApplicationResponse;
import com.example.jwt.demojwt.entities.Response.CreateAppResponse;
import com.example.jwt.demojwt.utils.TokenGenerator;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Qualifier("beanApplicationRep")
    private IApplicationRep _appRep;
    TokenGenerator tokenGenerator;

    @Autowired
    public ApplicationService(IApplicationRep rep) {
        _appRep = rep;
        tokenGenerator = new TokenGenerator();
    }

    public ApplicationService() {
        super();
    }

    public CreateAppResponse CreateApplication(AppRequest appReq) throws Exception {
        ValidateAppRequest(appReq);
        Application app = new Application();
        tokenGenerator.setKeys();
        app.setApplicationName(appReq.getApplicationName());
        app.setPrivateKey(tokenGenerator.getPrivateKey());
        app.setPublicKey(tokenGenerator.getPublicKey());
        UUID appCode = _appRep.createApplication(app);
        return new CreateAppResponse(appCode, app.getPublicKey());
    }

    public List<ApplicationResponse> GetAll(){
        List<Application> app = _appRep.getAll();
        List<ApplicationResponse> response = new ArrayList<ApplicationResponse>();

        app.forEach(a -> response.add(
            new ApplicationResponse(
                a.getApplicationName(),
                a.getApplicationCode(),
                a.getPublicKey())));

        return response;
    }

    private void ValidateAppRequest(AppRequest appReq){
        if(appReq == null){
            throw new CustomExceptions.BadRequestException("Por favor valide los datos requeridos e intente nuevamente");
        }

        if (appReq.getApplicationName().isEmpty()){
            throw new CustomExceptions.BadRequestException("El campo ApplicationName es obligatorio");
        }
    }
}