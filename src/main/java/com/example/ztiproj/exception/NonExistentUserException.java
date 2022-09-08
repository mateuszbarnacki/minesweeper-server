package com.example.ztiproj.exception;

import com.example.ztiproj.exception.handler.ErrorCode;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class NonExistentUserException extends ZtiProjException {
    public NonExistentUserException(String message) {
        super(message, ErrorCode.NOT_FOUND);
    }
}
