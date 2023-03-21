package com.example.demospringboot.model.common;

public class ErrorResponse extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(String message, int statusCode, String statusMessage) {
        super(message);
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
