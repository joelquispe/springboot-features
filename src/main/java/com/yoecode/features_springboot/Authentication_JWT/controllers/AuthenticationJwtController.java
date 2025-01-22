package com.yoecode.features_springboot.authentication_jwt.controllers;

import com.yoecode.features_springboot.authentication_jwt.dtos.SignInReqDto;
import com.yoecode.features_springboot.authentication_jwt.models.Auth;
import com.yoecode.features_springboot.authentication_jwt.services.CustomUserJwtService;
import com.yoecode.features_springboot.authentication_jwt.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;    
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-jwt")
public class AuthenticationJwtController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserJwtService userService;
    private final JwtUtil jwtUtil;

    public AuthenticationJwtController(AuthenticationManager authenticationManager,
                                     CustomUserJwtService userService,
                                     JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> registerUser(@RequestBody Auth user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> loginUser(@RequestBody SignInReqDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(token);
    }
}
