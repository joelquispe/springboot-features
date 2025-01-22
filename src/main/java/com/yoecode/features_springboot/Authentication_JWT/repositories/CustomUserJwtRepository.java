package com.yoecode.features_springboot.authentication_jwt.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoecode.features_springboot.authentication_jwt.models.Auth;

@Repository
public interface CustomUserJwtRepository extends JpaRepository<Auth, Long> {
    Auth findByUsername(String username);
}