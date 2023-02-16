package com.example.ztiproj.user;

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
