package com.example.weclean.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class AuthRequest {
    @NonNull
    @Getter
    @Setter
    private String login;

    @NonNull
    @Getter
    @Setter
    private String password;
}
