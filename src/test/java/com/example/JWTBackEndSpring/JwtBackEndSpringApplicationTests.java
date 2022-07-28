package com.example.JWTBackEndSpring;

import com.example.JWTBackEndSpring.exception.BaseException;
import com.example.JWTBackEndSpring.model.RegisterModel;
import com.example.JWTBackEndSpring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtBackEndSpringApplicationTests {
	@Autowired
	UserService userService;
	@Test
	void contextLoads() throws BaseException {
		RegisterModel registerModel = new RegisterModel();
		registerModel.setFirstName("Kanakarn");
		registerModel.setLastName("Charoenruengsakul");
		registerModel.setUserName("Foamkrab");
		registerModel.setPassWord("passwordtest");
		userService.createUser(registerModel);
	}

}
