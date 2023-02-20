package com.example.ztiproj.user;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-16
 */
public enum AuthRole {
    USER("ROLE_USER");
    private final String role;

    AuthRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
