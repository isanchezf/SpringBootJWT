package com.example.jwt.demojwt.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
public class holaController {

    @GetMapping(value="/hola")
	public String getMethodName(@RequestHeader(name="Authorization") String authorization)
	{
		return "Hola desde mi servicio";
	}
}