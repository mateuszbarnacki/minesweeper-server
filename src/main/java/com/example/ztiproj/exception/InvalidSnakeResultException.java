package com.example.ztiproj.exception;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.exception.handler.ErrorCode;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class InvalidSnakeResultException extends ZtiProjException {
    public InvalidSnakeResultException() {
        super(Labels.INVALID_SNAKE_DTO_EXCEPTION_MESSAGE, ErrorCode.BAD_REQUEST);
    }

    public InvalidSnakeResultException(String message) {
        super(message, ErrorCode.BAD_REQUEST);
    }
}
