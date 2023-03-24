package com.example.demospringboot.util.exception;

public class UserException extends BaseException {
    public UserException(String message) {
        super(message);
    }

    public static UserException dataNotFound() {
        return new UserException("ไม่พบข้อมูล");
    }

    public static UserException createUserNotFound() {
        return new UserException("ไม่สามารถสร้างข้อมูล User ได้");
    }

    public static UserException updateUserNotFound() {
        return new UserException("ไม่สามารถ Update ข้อมูล User ได้");
    }

    public static UserException valueIsNull(String param) {
        return new UserException("please input : " + param);
    }
}
