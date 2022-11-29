package com.example.demo2.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, NASA;

    @Override
    public String getAuthority() {
        return name();
    }
}
