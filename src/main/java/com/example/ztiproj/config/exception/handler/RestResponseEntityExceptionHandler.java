package com.example.ztiproj.config.exception.handler;

import com.example.ztiproj.config.exception.ExistentUserException;
import com.example.ztiproj.config.exception.InvalidMinesweeperResultException;
import com.example.ztiproj.config.exception.ZtiProjException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidMinesweeperResultException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleInvalidMinesweeperResultException(InvalidMinesweeperResultException e) {
        return new ResponseEntity<>(buildErrorMessage(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ExistentUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleExistentUserException(ExistentUserException e) {
        return new ResponseEntity<>(buildErrorMessage(e), HttpStatus.BAD_REQUEST);
    }

    private RestError buildErrorMessage(ZtiProjException e) {
        return new RestError(e.getMessage(), e.getErrorCode());
    }
}
