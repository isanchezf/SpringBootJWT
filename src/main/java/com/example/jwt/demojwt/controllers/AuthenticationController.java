package com.example.jwt.demojwt.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.example.jwt.demojwt.entities.Request.AuthenticationRequest;
import com.example.jwt.demojwt.entities.Response.AuthenticationResponse;
import com.example.jwt.demojwt.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService _authService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuthenticationResponse Authenticate(@Valid @RequestBody Optional<AuthenticationRequest> authReq) throws Exception {
        return _authService.Authenticate(authReq);
    }
}