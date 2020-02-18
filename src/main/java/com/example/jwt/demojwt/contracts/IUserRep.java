package com.example.jwt.demojwt.contracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.jwt.demojwt.entities.User;
import com.example.jwt.demojwt.entities.Request.AuthenticationRequest;

public interface IUserRep {
	UUID CreateUser(User user);
	List<User> GetAll();
	Optional<User> GetUserByCredentials(Optional<AuthenticationRequest> request);
}
