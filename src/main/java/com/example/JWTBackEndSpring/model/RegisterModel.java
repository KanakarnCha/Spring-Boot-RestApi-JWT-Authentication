package com.example.JWTBackEndSpring.model;

import lombok.Data;

@Data
public class RegisterModel {
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
}
