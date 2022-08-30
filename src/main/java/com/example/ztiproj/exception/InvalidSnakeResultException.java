package com.example.ztiproj.exception;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class InvalidSnakeResultException extends ZtiProjException {
    public InvalidSnakeResultException() {
        super("Invalid snake dto!", 400);
    }

    public InvalidSnakeResultException(String message) {
        super(message, 400);
    }
}
