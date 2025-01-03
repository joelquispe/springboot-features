package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.controllers;

import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.dtos.CreateUserDto;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities.User;
import com.yoecode.features_springboot.exampleUploadAndGenerateArchives.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    private  ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<String> create(@ModelAttribute CreateUserDto body) throws IOException {
        userService.create(body);
        return new ResponseEntity<>("Usuario creado",HttpStatus.OK);
    }
}
