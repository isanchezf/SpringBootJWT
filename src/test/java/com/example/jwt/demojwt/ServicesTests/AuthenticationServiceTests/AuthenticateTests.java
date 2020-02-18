package com.example.jwt.demojwt.ServicesTests.AuthenticationServiceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IApplicationRep;
import com.example.jwt.demojwt.contracts.IAuthorizationUserRep;
import com.example.jwt.demojwt.contracts.IUserRep;
import com.example.jwt.demojwt.entities.Application;
import com.example.jwt.demojwt.entities.User;
import com.example.jwt.demojwt.entities.Request.AuthenticationRequest;
import com.example.jwt.demojwt.entities.Response.AuthenticationResponse;
import com.example.jwt.demojwt.services.AuthenticationService;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AuthenticateTests {

    @Mock
    IUserRep _userRep;
    @Mock
    IAuthorizationUserRep _authorizationRep;
    @Mock
    IApplicationRep _appRep;

    AuthenticationService _authService;

    public AuthenticateTests() {
        MockitoAnnotations.initMocks(this);
        _authService = new AuthenticationService(_userRep, _authorizationRep, _appRep);
    }

    @Test(expected = CustomExceptions.BadRequestException.class)
    public void whenCredentialsIsNullOrEmptyReturnError() throws Exception {
        //Arrange
        Optional<AuthenticationRequest> request = Optional.of(new AuthenticationRequest("","", UUID.randomUUID()));
        //Action
        _authService.Authenticate(request);
        //Assertion
    }

    @Test(expected = CustomExceptions.NotFoundException.class)
    public void whenUserCredentialAreWrongThenReturnError() throws Exception{
        // Arrange
        UUID appCode = UUID.randomUUID();
        Optional<AuthenticationRequest> request = Optional.of(new AuthenticationRequest("Pepito", "123",appCode));
        Mockito.when(_userRep.GetUserByCredentials(request)).thenReturn(Optional.of(new User()));
        // Action
        _authService.Authenticate(request);
        // Assertion
    }

    @Test
    public void whenUserAuthorizationDoesNotExistsThenReturnError() {
        // Arrange
        UUID userCode = UUID.randomUUID();
        UUID appCode = UUID.randomUUID();
        Optional<AuthenticationRequest> request = Optional.of(new AuthenticationRequest("Pepito", "123",appCode));
        
        Mockito.when(_userRep.GetUserByCredentials(request)).thenReturn(Optional.of(new User(
            request.get().getUserName(),
            request.get().getUserPassword(),
            userCode
        )));
        Mockito.when(_authorizationRep.GetAuthorizationByUserAndApp(userCode, appCode)).thenReturn(false);
        String expectedMessage = "Usuario no autorizado";

        // Action
        Exception exception = assertThrows(CustomExceptions.NotFoundException.class, () -> {
            _authService.Authenticate(request);
        });
        
        // Assertion
        assertEquals(expectedMessage, exception.getMessage());
        Mockito.verify(_userRep, times(1)).GetUserByCredentials(request);        
        Mockito.verify(_authorizationRep, times(1)).GetAuthorizationByUserAndApp(userCode, appCode);        
    }

    @Test
    public void whenCredentialsOKAndUserAuthorizationThenReturnToken() throws Exception {
        // Arrange
        UUID userCode = UUID.randomUUID();
        UUID appCode = UUID.randomUUID();
        Optional<AuthenticationRequest> request = Optional.of(new AuthenticationRequest("Pepito", "123",appCode));
        Application app = new Application();
        app.setApplicationCode(appCode);  
        app.setPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ941mU447oE4ltmCzvRGh/zKX+cngnAjD9PjGeitthtQvxeoju/4vnGXyZxdYjIuyKIT+W6IT0YAbBwSM1tgYlNufZ9ExfnGYALtbrNsIdNRXJMzW/CMQsw3lz9AZMuAu8FEnIg405Ac9kr8H/YATK+mFGDeZVuJ4ayQ9YJfz3xAgMBAAECgYAbpvRmMOssAlTNzG/+O0/wYlW7zwiIYF7xS3XpMonKFYgcArzW53sQHJm7LMlYopXwcvqHtzK0SUlhstas6GQhiKXxA/4QAYD5CDIY/xKWRc9G4K7aqqnts/hy6MxR/1ef0R3hufHPxn7DChNuV09UL/h16b0oFN60JmWKmntRwQJBANdZyCOhp5Cj7t3FDjbp14BjLB9RTK9E4S9ttSfqjo0zdSrtB6o+c5FOs6AcwNSDkbMo75QztgwgxT4TglrYbL0CQQC9kuAAQYA06qbektf+xXwoq4/6WqGNjDCKcpK4AISXOOwvFK5/74QVF2pe5asgvRPE9IV6bCDBqNbI8X+QmhtFAkAICmsloXCPPv+5OhVYyYxpV8qa9L8nQCwkSDVeYyylawlx693AZoqMH2MnlEtC5BK5nMqtPu0KMOMMeVABslkFAkAjZCczhQb1WhVbGhj+9Elwok7X01GzxkdNoYQom9glDzhwtbC6K7IB6gQuwTvIeeQV8fx1VXhsDyRCEWvaSBrtAkBPwgK1xwjFL8gbTuc5+dZZZ+kfLhA8u9l8ZFVoQCGNgmWCRD+U8CXd1HXb18EhWp7mzH84DWxNEczftRrxJ25F");
        app.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfeNZlOOO6BOJbZgs70Rof8yl/nJ4JwIw/T4xnorbYbUL8XqI7v+L5xl8mcXWIyLsiiE/luiE9GAGwcEjNbYGJTbn2fRMX5xmAC7W6zbCHTUVyTM1vwjELMN5c/QGTLgLvBRJyIONOQHPZK/B/2AEyvphRg3mVbieGskPWCX898QIDAQAB");
        
        Mockito.when(_userRep.GetUserByCredentials(request)).thenReturn(Optional.of(new User(
            request.get().getUserName(),
            request.get().getUserPassword(),
            userCode
        )));
        Mockito.when(_authorizationRep.GetAuthorizationByUserAndApp(userCode, appCode)).thenReturn(true);
        Mockito.when(_appRep.getByCode(appCode)).thenReturn(app);


        // Action
        AuthenticationResponse result = _authService.Authenticate(request);
        
        // Assertion
        assertFalse(result.getToken().isEmpty());
        Mockito.verify(_userRep, times(1)).GetUserByCredentials(request);        
        Mockito.verify(_authorizationRep, times(1)).GetAuthorizationByUserAndApp(userCode, appCode);        
        Mockito.verify(_appRep, times(1)).getByCode(appCode);
    }
}