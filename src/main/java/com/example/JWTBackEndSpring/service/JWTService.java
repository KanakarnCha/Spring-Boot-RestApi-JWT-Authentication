package com.example.JWTBackEndSpring.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.JWTBackEndSpring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Service
public class JWTService {
    private final String secretName = "Secret";
    private final String issuer = "Backend";
    public Algorithm algorithm(){
        return  Algorithm.HMAC256(secretName);
    }
    public String createJWT(UserEntity user){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal",user.getId())
                .withClaim("role","USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }
    public DecodedJWT verifierJwt(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer).build();
            return verifier.verify(token);
        }catch (Exception e){
            return null;
        }

    }
}
