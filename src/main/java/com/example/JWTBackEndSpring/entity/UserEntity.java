package com.example.JWTBackEndSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)

public class UserEntity extends  BaseEntity{
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;

}
