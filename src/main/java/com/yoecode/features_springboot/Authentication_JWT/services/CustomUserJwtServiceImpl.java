package com.yoecode.features_springboot.authentication_jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.yoecode.features_springboot.authentication_jwt.models.AuthReq;
import com.yoecode.features_springboot.authentication_jwt.repositories.CustomUserJwtRepository;

import java.util.ArrayList;

@Service
public class CustomUserJwtServiceImpl implements CustomUserJwtService {

    private final CustomUserJwtRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserJwtServiceImpl(CustomUserJwtRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthReq user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    @Override
    public void saveUser(AuthReq user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}