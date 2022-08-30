package com.example.ztiproj.exception;

import com.example.ztiproj.exception.handler.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/error")
public class DummyErrorController {

    @GetMapping("/minesweeper")
    public ResponseEntity<RestError> dummyMinesweeperController() {
        throw new InvalidMinesweeperResultException("Null dto provided!");
    }

    @GetMapping("/snake")
    public ResponseEntity<RestError> dummySnakeController() {
        throw new InvalidSnakeResultException("Null dto provided!");
    }

    @GetMapping("/tetris")
    public ResponseEntity<RestError> dummyTetrisController() {
        throw new InvalidTetrisResultException("Null dto provided!");
    }

    @GetMapping("/user-not-found")
    public ResponseEntity<RestError> dummyUserNotFoundController() {
        throw new NonExistentUserException("User does not exists!");
    }
}
