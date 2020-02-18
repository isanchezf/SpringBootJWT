package com.example.jwt.demojwt.ServicesTests.UserTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.UUID;

import com.example.jwt.demojwt.contracts.IUserRep;
import com.example.jwt.demojwt.entities.User;
import com.example.jwt.demojwt.entities.Request.UserRequest;
import com.example.jwt.demojwt.services.UserService;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CreateUserTests {

    @Mock()
    IUserRep _userRep;
    UserService _userService;
    

    public CreateUserTests() {
        MockitoAnnotations.initMocks(this);
        _userService = new UserService(_userRep);
    }

    @Test(expected = CustomExceptions.BadRequestException.class)
    public void WhenUserNameIsNullOrEmptyReturnError()
    {
        //Arrange
        UserRequest request = new UserRequest();
        //Action
        _userService.CreateUser(request);
        //Assert
    }
    
    @Test(expected = CustomExceptions.BadRequestException.class)
    public void WhenUserPasswordIsNullOrEmptyReturnError()
    {
        //Arrange
        UserRequest request = new UserRequest();
        request.setUserName("userTest");
        //Action
        _userService.CreateUser(request);
        //Assert
    }

    @Test()
    public void WhenUserRequestIsCorrectReturnOk()
    {
        //Arrange
        UserRequest request = new UserRequest();
        UUID userCode = UUID.randomUUID();
        request.setUserName("userTest");
        request.setUserPassword("123456");
        Mockito.when(_userRep.CreateUser(any(User.class))).thenReturn(userCode);

        //Action
        UUID response = _userService.CreateUser(request);
        //Assert
        assertTrue(response.compareTo(userCode)==0);
        Mockito.verify(_userRep, times(1)).CreateUser(any(User.class));
    }
}