package com.example.jwt.demojwt.ServicesTests.ApplicationServiceTests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.UUID;

import com.example.jwt.demojwt.contracts.IApplicationRep;
import com.example.jwt.demojwt.entities.Application;
import com.example.jwt.demojwt.entities.Request.AppRequest;
import com.example.jwt.demojwt.entities.Response.CreateAppResponse;
import com.example.jwt.demojwt.services.ApplicationService;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CreateApplicationTests {

    @Mock
    IApplicationRep _appRep;
    ApplicationService _appService;

    public CreateApplicationTests() {
        MockitoAnnotations.initMocks(this);
        _appService = new ApplicationService(_appRep);
    }

    @Test(expected = CustomExceptions.BadRequestException.class)
    public void WhenApplicationNameIsNullOrEmptyReturnError() throws Exception {
        //Arrange
        AppRequest appReq = new AppRequest();
        //Action
        _appService.CreateApplication(appReq);
        //Assertion
    }
    
    @Test()
    public void WhenRequestIsCorrectReturnOk() throws Exception {
        //Arrange
        UUID appCode = UUID.randomUUID();
        AppRequest appReq = new AppRequest();
        appReq.setApplicationName("AppFromTests");
        Mockito.when(_appRep.createApplication(any(Application.class))).thenReturn(appCode);

        //Action
        CreateAppResponse response = _appService.CreateApplication(appReq);
        //Assertions
        assertTrue(response!=null);
        assertTrue(response.getApplicationCode().compareTo(appCode)==0);
        assertFalse(response.getPublicKey().isEmpty());
        Mockito.verify(_appRep, times(1)).createApplication(any(Application.class));
    }
}