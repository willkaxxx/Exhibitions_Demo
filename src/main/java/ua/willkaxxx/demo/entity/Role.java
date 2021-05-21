package ua.willkaxxx.demo.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    AUTHORIZED, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
