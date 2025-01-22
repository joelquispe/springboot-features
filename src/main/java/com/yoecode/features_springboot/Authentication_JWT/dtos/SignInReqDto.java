package com.yoecode.features_springboot.authentication_jwt.dtos;

import lombok.Data;

@Data()
public class SignInReqDto {
    private String username;
    private String password;
}
