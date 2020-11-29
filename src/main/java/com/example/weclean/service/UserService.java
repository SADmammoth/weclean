package com.example.weclean.service;

import com.example.weclean.domain.User;
import com.example.weclean.domain.enums.Role;
import com.example.weclean.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        Optional<User> userEntity = findByLogin(login);
        if (userEntity.isPresent()) {
            if (passwordEncoder.matches(password, userEntity.get().getPassword())) {
                return userEntity;
            }
        }
        return Optional.empty();
    }
}