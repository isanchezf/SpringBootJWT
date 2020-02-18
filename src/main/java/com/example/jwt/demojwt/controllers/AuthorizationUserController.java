package com.example.jwt.demojwt.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.jwt.demojwt.entities.AuthorizationUser;
import com.example.jwt.demojwt.entities.Request.AuthorizationUserRequest;
import com.example.jwt.demojwt.services.AuthorizationUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AuthorizationUserController {

    @Autowired
    AuthorizationUserService _service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UUID CreateAuthorizationUser(@Valid @RequestBody AuthorizationUserRequest request) throws Exception {
        return _service.CreateAuthorizationUser(request);   
    }
    
    @GetMapping()
    public @ResponseBody List<AuthorizationUser> GetAll(){
        return _service.GetAll();
    }
}