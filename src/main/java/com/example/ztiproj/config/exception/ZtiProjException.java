package com.example.ztiproj.config.exception;

import com.example.ztiproj.config.exception.handler.ErrorCode;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class ZtiProjException extends RuntimeException {
    private final ErrorCode errorCode;

    public ZtiProjException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
