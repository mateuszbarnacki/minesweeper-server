package com.example.ztiproj.exception;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class InvalidMinesweeperResultException extends ZtiProjException {
    public InvalidMinesweeperResultException() {
        super("Invalid minesweeper dto!", 400);
    }

    public InvalidMinesweeperResultException(String message) {
        super(message, 400);
    }
}
