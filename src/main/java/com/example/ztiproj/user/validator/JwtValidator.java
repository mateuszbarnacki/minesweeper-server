package com.example.ztiproj.user.validator;

import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtValidator {
    ValidationResult validate(Jwt jwt);
}
