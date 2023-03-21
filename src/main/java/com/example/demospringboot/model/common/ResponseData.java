package com.example.demospringboot.model.common;

import java.util.ArrayList;
import java.util.Optional;

public class ResponseData {

    private String statusCode;
    private String statusMessage;
    private ArrayList result;

    public ResponseData(String statusCode, String statusMessage, ArrayList result) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public ArrayList getResult() {
        return result;
    }

    public void setResult(ArrayList result) {
        this.result = result;
    }
}
