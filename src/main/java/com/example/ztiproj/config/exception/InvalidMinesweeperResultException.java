package com.example.ztiproj.config.exception;

import com.example.ztiproj.minesweeper.common.Labels;
import com.example.ztiproj.config.exception.handler.ErrorCode;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public class InvalidMinesweeperResultException extends ZtiProjException {
    public InvalidMinesweeperResultException() {
        super(Labels.INVALID_MINESWEEPER_DTO_EXCEPTION_MESSAGE, ErrorCode.BAD_REQUEST);
    }

    public InvalidMinesweeperResultException(String message) {
        super(message, ErrorCode.BAD_REQUEST);
    }
}
