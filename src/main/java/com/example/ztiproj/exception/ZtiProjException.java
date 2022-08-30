package com.example.ztiproj.exception;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class ZtiProjException extends RuntimeException {
    private final int errorCode;

    public ZtiProjException(String msg, int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
