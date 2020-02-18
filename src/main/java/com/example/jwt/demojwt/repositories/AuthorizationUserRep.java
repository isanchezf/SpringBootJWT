package com.example.jwt.demojwt.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IAuthorizationUserRep;
import com.example.jwt.demojwt.entities.AuthorizationUser;

import org.springframework.stereotype.Component;

@Component("beanAuthorizationUserRep")
public class AuthorizationUserRep implements IAuthorizationUserRep {

    List<AuthorizationUser> userList;

    public AuthorizationUserRep() {
        userList = new ArrayList<AuthorizationUser>();

    }
    
    public UUID CreateAuthorizationUser(AuthorizationUser any) {
        userList.add(any);
        return any.getAuthorizationCode();
    }
    
    public List<AuthorizationUser> GetAll(){
        return userList;
    }

    public Boolean GetAuthorizationByUserAndApp(UUID userCode, UUID appCode) {
        return userList.stream().filter(u -> 
        u.getUserCode().compareTo(userCode) == 0 &&
        u.getApplicationCode().compareTo(appCode) == 0).findFirst().isPresent();
    }
}