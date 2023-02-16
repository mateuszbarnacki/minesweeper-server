package com.example.ztiproj.minesweeper.service;

import com.example.ztiproj.minesweeper.dto.MinesweeperDto;

import java.util.Collection;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-29
 */
public interface MinesweeperService {

    /**
     * This method is used to retrieve the best game results.
     *
     * @return Collection<MinesweeperDto> This is the collection of the best results.
     */
    Collection<MinesweeperDto> getRanking();

    /**
     * This method is used to retrieve 10 best user results.
     *
     * @param userName This is the public name of the user, which is used to retrieve results.
     * @return Collection<MinesweeperDto> This is the Collection of the best user results.
     */
    Collection<MinesweeperDto> getUserRanking(String userName);

    /**
     * This method is used to add result to the database.
     *
     * @param minesweeperDto This is the object which going to be saved in the database.
     * @return MinesweeperDto This is the object which contains saved data.
     * @throws IllegalArgumentException If the parameter is invalid, method throws exception.
     */
    MinesweeperDto addResult(MinesweeperDto minesweeperDto);

    /**
     * This method deletes all users with specified userName from database.
     *
     * @param userName This is the public name of the user.
     */
    void deleteAllUserResults(String userName);

}
