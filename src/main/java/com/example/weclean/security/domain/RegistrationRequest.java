package com.example.weclean.security.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


public class RegistrationRequest {
    @NonNull
    @Getter
    @Setter
    private String login;

    @NonNull
    @Getter
    @Setter
    private String password;

    @NonNull
    @Getter
    @Setter
    private String role;
}
