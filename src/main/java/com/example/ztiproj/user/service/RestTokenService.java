package com.example.ztiproj.user.service;

import com.example.ztiproj.user.jwt.JwtFactory;
import com.example.ztiproj.user.jwt.TokenValidator;
import com.example.ztiproj.user.validator.ValidationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2023-02-08
 */
@Service
public class RestTokenService implements TokenService {
    private static final Logger LOGGER = Logger.getLogger(RestTokenService.class.getName());
    private final JwtFactory jwtFactory;
    private final TokenValidator tokenValidator;

    public RestTokenService(JwtFactory jwtService, TokenValidator tokenValidator) {
        this.jwtFactory = jwtService;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public String generateToken(Authentication authentication) {
        Jwt jwt = jwtFactory.generate(authentication);
        return jwt.getTokenValue();
    }

    @Override
    public boolean checkToken(String token) {
        ValidationResult validationResult = tokenValidator.validate(token);
        return isTokenValid(validationResult);
    }

    private boolean isTokenValid(ValidationResult validationResult) {
        boolean isTokenValid = validationResult.isValid();
        if (!isTokenValid) {
            LOGGER.log(Level.WARNING, "JWT error occurred: {0}", validationResult.getErrorMessage());
        }
        return isTokenValid;
    }
}
