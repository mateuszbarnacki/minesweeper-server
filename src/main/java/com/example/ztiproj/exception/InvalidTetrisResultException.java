package com.example.ztiproj.exception;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.exception.handler.ErrorCode;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class InvalidTetrisResultException extends ZtiProjException {
    public InvalidTetrisResultException() {
        super(Labels.INVALID_TETRIS_DTO_EXCEPTION_MESSAGE, ErrorCode.BAD_REQUEST);
    }

    public InvalidTetrisResultException(String message) {
        super(message, ErrorCode.BAD_REQUEST);
    }
}
