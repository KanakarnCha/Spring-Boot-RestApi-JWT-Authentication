package com.example.JWTBackEndSpring.controller;

import com.example.JWTBackEndSpring.entity.UserEntity;
import com.example.JWTBackEndSpring.exception.BaseException;
import com.example.JWTBackEndSpring.exception.UserException;
import com.example.JWTBackEndSpring.model.LoginModel;
import com.example.JWTBackEndSpring.model.RegisterModel;
import com.example.JWTBackEndSpring.repository.UserRepository;
import com.example.JWTBackEndSpring.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manager")
public class UserApi {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity<List<UserEntity>> getAll() throws BaseException {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody RegisterModel registerModel) throws BaseException {
        return new ResponseEntity<>(userService.createUser(registerModel),HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> userRegister(@RequestBody LoginModel loginModel) throws BaseException {
        return new ResponseEntity<>(userService.userLogin(loginModel),HttpStatus.OK);
    }
}
