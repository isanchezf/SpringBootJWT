package com.example.jwt.demojwt.ServicesTests.AuthorizationUserServiceTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.UUID;

import com.example.jwt.demojwt.contracts.IAuthorizationUserRep;
import com.example.jwt.demojwt.entities.AuthorizationUser;
import com.example.jwt.demojwt.entities.Request.AuthorizationUserRequest;
import com.example.jwt.demojwt.services.AuthorizationUserService;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CreateAuthorizationTests {

    @Mock()
    IAuthorizationUserRep _authRep;
    AuthorizationUserService _authService; 
    
    public CreateAuthorizationTests() {
        MockitoAnnotations.initMocks(this);
        _authService = new AuthorizationUserService(_authRep);
    }

    @Test(expected = CustomExceptions.BadRequestException.class)
    public void whenUserNameDoesNotExistsReturnError(){
        //Arrange
        AuthorizationUserRequest request = new AuthorizationUserRequest();
        request.setApplicationCode(UUID.randomUUID());
        //Action
        _authService.CreateAuthorizationUser(request);
        //Assertion
    }

    @Test(expected = CustomExceptions.BadRequestException.class)
    public void whenApplicationNameDoesNotExistsReturnError(){
        //Arrange
        AuthorizationUserRequest request = new AuthorizationUserRequest();
        request.setUserCode(UUID.randomUUID());
        //Action
        _authService.CreateAuthorizationUser(request);
        //Assertion
    }

    @Test()
    public void whenAuthorizationUserRequestIsCorrectReturnSuccess(){
        //Arrange
        UUID authCode = UUID.randomUUID();
        UUID userCode = UUID.randomUUID();
        UUID appCode = UUID.randomUUID();
        AuthorizationUserRequest request = new AuthorizationUserRequest();       
        request.setApplicationCode(appCode);
        request.setUserCode(userCode);
        Mockito.when(_authRep.CreateAuthorizationUser(any(AuthorizationUser.class))).thenReturn(authCode);
        //Action
        UUID response = _authService.CreateAuthorizationUser(request);

        //Assertion
        assertTrue(response!=null);
        assertTrue(response.equals(authCode));
        Mockito.verify(_authRep, times(1)).CreateAuthorizationUser(any(AuthorizationUser.class));
    }
}