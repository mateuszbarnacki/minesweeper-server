package com.example.ztiproj.user.validator;

import org.springframework.security.oauth2.jwt.Jwt;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
public interface JwtValidator {
    ValidationResult validate(Jwt jwt);
}
