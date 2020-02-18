package com.example.jwt.demojwt.contracts;

import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.entities.Application;
import org.springframework.stereotype.Service;

@Service
public interface IApplicationRep {
    UUID createApplication(final Application app);
    List<Application> getAll();
    Application getByCode(UUID appCode);
}