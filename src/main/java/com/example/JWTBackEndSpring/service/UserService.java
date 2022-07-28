package com.example.JWTBackEndSpring.service;

import com.example.JWTBackEndSpring.entity.UserEntity;
import com.example.JWTBackEndSpring.exception.BaseException;
import com.example.JWTBackEndSpring.exception.UserException;
import com.example.JWTBackEndSpring.model.LoginModel;
import com.example.JWTBackEndSpring.model.RegisterModel;
import com.example.JWTBackEndSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTService jwtService;
    public String createUser(RegisterModel registerModel) throws BaseException {
        if (Objects.equals(registerModel.getFirstName(), "") || registerModel.getFirstName() == null){
            throw UserException.notFound();
        }
        if (Objects.equals(registerModel.getLastName(), "") || registerModel.getLastName() == null){
            throw UserException.notFound();
        }
        if (Objects.equals(registerModel.getUserName(), "") || registerModel.getUserName() == null){
            throw UserException.notFound();
        }
        if (Objects.equals(registerModel.getPassWord(), "") || registerModel.getPassWord() == null){
            throw UserException.notFound();
        }
        registerModel.setPassWord(passwordEncoder.encode(registerModel.getPassWord()));
        UserEntity user = new UserEntity();
        user.setFirstName(registerModel.getFirstName());
        user.setLastName(registerModel.getLastName());
        user.setUserName(registerModel.getUserName());
        user.setPassWord(registerModel.getPassWord());
        userRepository.save(user);
        return "Register Success";
    }
    public String userLogin(@RequestBody LoginModel loginModel) throws UserException {
        if (Objects.equals(loginModel.getUserName(), "") || loginModel.getUserName() == null){
            throw UserException.notFound();
        }
        if (Objects.equals(loginModel.getPassWord(), "") || loginModel.getPassWord() == null){
            throw UserException.notFound();
        }
        Optional<UserEntity> user = userRepository.findByUserName(loginModel.getUserName());
        if (user.isEmpty()){
            throw UserException.notFound();
        }
        boolean checkPass = passwordEncoder.matches(loginModel.getPassWord(),user.get().getPassWord());
        if (!checkPass){
            throw  UserException.notFound();
        }
        return jwtService.createJWT(user.get());
    }
}
