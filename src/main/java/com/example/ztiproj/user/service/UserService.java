package com.example.ztiproj.user.service;

import com.example.ztiproj.user.dto.AuthenticationDto;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-20
 */
public interface UserService {
    /**
     * This method is used to register new user.
     *
     * @param authenticationDto Information about user.
     */
    void registerUser(AuthenticationDto authenticationDto);
}
