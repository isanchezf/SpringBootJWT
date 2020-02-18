package com.example.jwt.demojwt.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.jwt.demojwt.entities.Request.UserRequest;
import com.example.jwt.demojwt.entities.Response.UserResponse;
import com.example.jwt.demojwt.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService _userService;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UUID CreateUser(@Valid @RequestBody UserRequest userReq) throws Exception {
        return _userService.CreateUser(userReq);   
    }
    
    @GetMapping()
    public @ResponseBody List<UserResponse> GetAll(){
        return _userService.GetAll();
    }
}