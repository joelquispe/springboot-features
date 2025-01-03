package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateUserDto {
    private String username ;
    private String email;
    private String password;
    private MultipartFile photo;
}
