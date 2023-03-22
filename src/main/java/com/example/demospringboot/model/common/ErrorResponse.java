package com.example.demospringboot.model.common;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String statusMessage;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
