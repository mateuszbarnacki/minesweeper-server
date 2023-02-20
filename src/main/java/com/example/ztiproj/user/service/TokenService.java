package com.example.ztiproj.user.service;

import org.springframework.security.core.Authentication;

/**
 * @author Mateusz Barnacki
 * @version 2.0
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

    /**
     * This method is used to check JWT token correctness.
     *
     * @param token JWT token.
     * @return If token is valid returns true, otherwise false.
     */
    boolean checkToken(String token);
}
