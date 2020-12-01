package com.example.weclean.security.domain;

import lombok.Getter;

public enum Role {
    ADMIN("admin"), APPLICATION("application");
    @Getter
    private final String name;

    Role(String name){
        this.name = name;
    }

}
