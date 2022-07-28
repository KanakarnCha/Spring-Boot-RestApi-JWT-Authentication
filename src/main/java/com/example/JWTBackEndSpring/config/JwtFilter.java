package com.example.JWTBackEndSpring.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.JWTBackEndSpring.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    final JWTService jwtService;

    public JwtFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorization = request.getHeader("Authorization");
        if (authorization == null){
            filterChain.doFilter(request,response);
            return;
        }
        if(!authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorization.substring(7, authorization.length());
        DecodedJWT  tokenDecode =  jwtService.verifierJwt(token);
        if (tokenDecode == null){
            filterChain.doFilter(request,response);
            return;
        }
        String principal = String.valueOf(tokenDecode.getClaim("principal"));
        String role = String.valueOf(tokenDecode.getClaim("role"));
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(role));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,"(PasswordSecret)",authorityList);
        System.out.println("Check : --> "+authenticationToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
    }

