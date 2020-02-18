package com.example.jwt.demojwt.utils.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptions {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public NotFoundException(String errorMessage) {
            super(errorMessage);
        }
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class BadRequestException extends RuntimeException{

        /**
         *
         */
        private static final long serialVersionUID = 5434820606536135560L;

        public BadRequestException(String errorMessage) {
            super(errorMessage);
        }
       

    }
}