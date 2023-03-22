package com.example.demospringboot.util.exception;

public class UserException extends BaseException{
    public UserException(String message) {
        super(message);
    }

    public static UserException dataNotFound() {
        return new UserException("ไม่พบข้อมูล");
    }
}
