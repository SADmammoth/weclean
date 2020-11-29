package com.example.weclean.controller;

import com.example.weclean.config.JwtProvider;
import com.example.weclean.domain.AuthRequest;
import com.example.weclean.domain.RegistrationRequest;
import com.example.weclean.domain.User;
import com.example.weclean.domain.enums.Role;
import com.example.weclean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setRole(Role.valueOf(registrationRequest.getRole()));
        userService.saveUser(u);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthRequest request) {
        Optional<User> userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(userEntity.isPresent()){
            String token = jwtProvider.generateToken(userEntity.get().getLogin());
            return  ResponseEntity.ok().body(token);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
