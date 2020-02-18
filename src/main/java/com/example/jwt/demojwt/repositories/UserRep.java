package com.example.jwt.demojwt.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IUserRep;
import com.example.jwt.demojwt.entities.User;
import com.example.jwt.demojwt.entities.Request.AuthenticationRequest;

import org.springframework.stereotype.Component;

@Component("benUserRep")
public class UserRep implements IUserRep {
    private static List<User> userList;

    public UserRep() {
        userList = new ArrayList<User>();
        final User userOne = new User("userTestOne", "1234", UUID.fromString("cbcada6c-a67e-475b-a519-63457b893a96"));
        final User userTwo = new User("userTestTwo", "1234", UUID.fromString("b7c3f860-4e18-43bf-b096-c96a7fcfac60"));
        userList.add(userOne);
        userList.add(userTwo);
    }

    public UUID CreateUser(final User user) {
        userList.add(user);
        return user.getUserCode();
    }

    public List<User> GetAll() {
        return userList;
    }

    public Optional<User> GetUserByCredentials(final Optional<AuthenticationRequest> request) {
        return userList.stream().filter(u -> 
        u.getUserName().equals(request.get().getUserName()) &&
        u.getUserPassword().equals(request.get().getUserPassword())).findFirst();
    }
}