package com.example.demospringboot.util.exception;

import com.example.demospringboot.model.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice  {

    public ExceptionAdvice() {
        super();
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(BaseException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setStatusMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

//    @ResponseBody
//    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
        errorResponse.setStatusMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatusMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
