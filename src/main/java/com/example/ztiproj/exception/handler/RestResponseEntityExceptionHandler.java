package com.example.ztiproj.exception.handler;

import com.example.ztiproj.exception.InvalidMinesweeperResultException;
import com.example.ztiproj.exception.InvalidSnakeResultException;
import com.example.ztiproj.exception.InvalidTetrisResultException;
import com.example.ztiproj.exception.NonExistentUserException;
import com.example.ztiproj.exception.ZtiProjException;
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

    @ExceptionHandler({InvalidSnakeResultException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleInvalidSnakeResultException(InvalidSnakeResultException e) {
        return new ResponseEntity<>(buildErrorMessage(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidTetrisResultException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleInvalidTetrisResultException(InvalidTetrisResultException e) {
        return new ResponseEntity<>(buildErrorMessage(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NonExistentUserException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestError> handleNonExistentUserException(NonExistentUserException e) {
        return new ResponseEntity<>(buildErrorMessage(e), HttpStatus.NOT_FOUND);
    }

    private RestError buildErrorMessage(ZtiProjException e) {
        return RestError.builder()
                .message(e.getMessage())
                .code(e.getErrorCode())
                .build();
    }
}
