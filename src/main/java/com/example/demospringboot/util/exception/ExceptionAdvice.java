package com.example.demospringboot.util.exception;

import com.example.demospringboot.model.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice  {

    public ExceptionAdvice() {
        super();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ErrorResponse.class)
    ErrorResponse errorExceptionResponse(ErrorResponse error) {
        return error;
    }
}
