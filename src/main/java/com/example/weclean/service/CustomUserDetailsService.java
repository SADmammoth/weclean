package com.example.weclean.service;

import com.example.weclean.config.CustomUserDetails;
import com.example.weclean.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntity = userService.findByLogin(username);
        if(userEntity.isPresent()){
            return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity.get());
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }
}