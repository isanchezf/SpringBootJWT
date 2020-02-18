package com.example.jwt.demojwt.entities.Response;

import java.util.Date;

public class ErrorResponse {
    private String code;
    private String type;
    private String message;
    private Date date;

    public ErrorResponse(String code, String type, String message, Date date) {
        this.setCode(code);
        this.setType(type);
        this.setMessage(message);
        this.setDate(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}