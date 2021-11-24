package com.example.coffeapp.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    BARISTA;

    @Override
    public String getAuthority() {
        return name();
    }
}
