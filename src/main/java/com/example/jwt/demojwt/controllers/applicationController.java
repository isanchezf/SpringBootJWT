package com.example.jwt.demojwt.controllers;

import java.util.List;

import javax.validation.Valid;
import com.example.jwt.demojwt.entities.Request.AppRequest;
import com.example.jwt.demojwt.entities.Response.ApplicationResponse;
import com.example.jwt.demojwt.entities.Response.CreateAppResponse;
import com.example.jwt.demojwt.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class applicationController {

    @Autowired
    ApplicationService _appService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CreateAppResponse CreateApp(@Valid @RequestBody AppRequest appReq) throws Exception {
        return _appService.CreateApplication(appReq);   
    }
    
    @GetMapping()
    public @ResponseBody List<ApplicationResponse> GetAll(){
        return _appService.GetAll();
    }
}