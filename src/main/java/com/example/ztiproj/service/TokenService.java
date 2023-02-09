package com.example.ztiproj.service;

import org.springframework.security.core.Authentication;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2023-02-09
 */
public interface TokenService {
    /**
     * This method is used to generate JWT token.
     *
     * @param authentication Information about user.
     * @return String Generated token.
     */
    String generateToken(Authentication authentication);
}
