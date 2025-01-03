package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.services;

import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.dtos.CreateUserDto;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities.User;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(CreateUserDto body) throws IOException {

            MultipartFile photo = body.getPhoto();
            String fileName = photo.getOriginalFilename();
            String path = "uploads/"+ fileName;

            File directory = new File("uploads");
            String absolutePath = new File(path).getAbsolutePath();
            if(!directory.exists()){
                directory.mkdir();
            }

            photo.transferTo(new File(absolutePath));
            User data = User.builder()
                    .username(body.getUsername())
                    .password(body.getPassword())
                    .email(body.getEmail())
                    .photo(path)
                    .build();
            return userRepository.save(data);

    }
}
