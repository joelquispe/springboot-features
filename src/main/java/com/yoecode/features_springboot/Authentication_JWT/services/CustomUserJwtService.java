package com.yoecode.features_springboot.authentication_jwt.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yoecode.features_springboot.authentication_jwt.models.Auth;


@Service
public interface CustomUserJwtService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    void saveUser(Auth user);
}

