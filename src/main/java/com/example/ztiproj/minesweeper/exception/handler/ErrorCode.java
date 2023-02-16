package com.example.ztiproj.minesweeper.exception.handler;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-09-08
 */
public enum ErrorCode {
    BAD_REQUEST(400),
    NOT_FOUND(404);

    private final int codeNumber;

    ErrorCode(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    public int getCodeNumber() {
        return codeNumber;
    }
}
