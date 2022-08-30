package com.example.ztiproj.service.api;

import com.example.ztiproj.dto.UserDto;
import com.example.ztiproj.exception.NonExistentUserException;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-29
 */
public interface UserService {

    /**
     * This method retrieves user data using username.
     *
     * @param username This is the public name of the user.
     * @return UserDto This object contains all user data.
     */
    UserDto getUserByUserName(String username);

    /**
     * This method deletes all the users with specified userName from database.
     *
     * @param userDto This is the object which going to be saved in the database.
     * @return UserDto This is the object which contains saved data.
     * @throws IllegalArgumentException If the parameter is invalid, method throws exception.
     */
    UserDto addUser(UserDto userDto);

    /**
     * This method check if the user exists in the database.
     *
     * @param username This is the public name of the user.
     * @throws NonExistentUserException If the user does not exist, method throws exception.
     */
    void checkUser(String username);
}
