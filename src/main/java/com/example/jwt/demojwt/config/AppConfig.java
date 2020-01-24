package com.example.jwt.demojwt.config;

import com.example.jwt.demojwt.filters.AuthorizationFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public FilterRegistrationBean < AuthorizationFilter > filterRegistrationBean() {
        FilterRegistrationBean < AuthorizationFilter > registrationBean = new FilterRegistrationBean<>();
        AuthorizationFilter authorizeFilter = new AuthorizationFilter();
        registrationBean.setFilter(authorizeFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1); //set precedence
        return registrationBean;
    }
    
}