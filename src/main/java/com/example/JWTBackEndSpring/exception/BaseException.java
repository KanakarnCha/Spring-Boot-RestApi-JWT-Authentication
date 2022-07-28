package com.example.JWTBackEndSpring.exception;

public class BaseException extends Exception{
    public BaseException(String code) {
        super("ERROR --> "+code);
    }
}
