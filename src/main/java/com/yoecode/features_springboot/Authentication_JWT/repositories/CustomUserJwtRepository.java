package com.yoecode.features_springboot.authentication_jwt.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yoecode.features_springboot.authentication_jwt.models.AuthReq;

public interface CustomUserJwtRepository extends JpaRepository<AuthReq, Long> {
    AuthReq findByUsername(String username);
}