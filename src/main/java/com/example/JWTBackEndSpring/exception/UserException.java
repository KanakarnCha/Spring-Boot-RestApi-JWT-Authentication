package com.example.JWTBackEndSpring.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super(code);
    }
    public static UserException notFound(){
        return new UserException("Not Found");
    }
}
