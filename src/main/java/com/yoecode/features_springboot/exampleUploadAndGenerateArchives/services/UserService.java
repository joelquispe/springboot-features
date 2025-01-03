package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.services;

import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.dtos.CreateUserDto;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(CreateUserDto body) throws IOException;
}
