package com.example.weclean.domain;

import com.example.weclean.domain.enums.Role;
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
