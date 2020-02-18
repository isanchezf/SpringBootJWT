package com.example.jwt.demojwt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IUserRep;
import com.example.jwt.demojwt.entities.User;
import com.example.jwt.demojwt.entities.Request.UserRequest;
import com.example.jwt.demojwt.entities.Response.UserResponse;
import com.example.jwt.demojwt.utils.CustomExceptions.CustomExceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Qualifier("beanUserRep")
    private IUserRep _userRep;

    @Autowired
    public UserService(IUserRep userRep) {
        this._userRep = userRep;
    }

	public UUID CreateUser(UserRequest request) {
        ValidateUserRequest(request);
        User user = new User(
            request.getUserName(),
            request.getUserPassword(), 
            UUID.randomUUID());
        return _userRep.CreateUser(user);
    }

    public List<UserResponse> GetAll()
    {
        List<User> userList = _userRep.GetAll();
        List<UserResponse> response = new ArrayList<UserResponse>();

        userList.forEach(u -> response.add(
            new UserResponse(u.getUserCode(), u.getUserName())));
        return response;
    }
    
    private void ValidateUserRequest(UserRequest request){
        if(request == null){
            throw new CustomExceptions.BadRequestException("Por favor validar los datos obligatorios");
        }
        if(request.getUserName().isEmpty() || request.getUserPassword().isEmpty()){
            throw new CustomExceptions.BadRequestException("Por favor validar los datos obligatorios");
        }
    }
}
