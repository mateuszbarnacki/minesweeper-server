package com.example.ztiproj.service.api;

import com.example.ztiproj.dto.SnakeDto;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-29
 */
public interface SnakeService {

    /**
     * This method is used to retrieve the best game results.
     *
     * @return List<SnakeDto> This is the list of the best results.
     */
    List<SnakeDto> getRanking();

    /**
     * This method is used to retrieve 10 best user results.
     *
     * @param userName This is the public name of the user, which is used to retrieve results.
     * @return List<SnakeDto> This is the list of the best user results.
     */
    List<SnakeDto> getUserRanking(String userName);

    /**
     * This method is used to add result to the database.
     *
     * @param snakeDto This is the object which going to be saved in the database.
     * @return SnakeDto This is the object which contains saved data.
     * @throws IllegalArgumentException If the parameter is invalid, method throws exception.
     */
    SnakeDto addResult(SnakeDto snakeDto);

    /**
     * This method deletes all users with specified userName from database.
     *
     * @param userName This is the public name of the user.
     * @return Nothing.
     */
    void deleteAllUserResults(String userName);

}
