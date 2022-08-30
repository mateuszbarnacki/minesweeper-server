package com.example.ztiproj.exception;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class InvalidTetrisResultException extends ZtiProjException {
    public InvalidTetrisResultException() {
        super("Invalid tetris dto!", 400);
    }

    public InvalidTetrisResultException(String message) {
        super(message, 400);
    }
}
