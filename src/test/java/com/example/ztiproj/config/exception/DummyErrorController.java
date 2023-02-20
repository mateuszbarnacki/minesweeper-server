package com.example.ztiproj.config.exception;

import com.example.ztiproj.config.exception.handler.RestError;
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

    @GetMapping("/user")
    public ResponseEntity<RestError> dummyUserController() {
        throw new ExistentUserException("test");
    }
}
