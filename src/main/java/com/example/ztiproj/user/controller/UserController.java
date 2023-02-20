package com.example.ztiproj.user.controller;

import com.example.ztiproj.user.dto.AuthenticationDto;
import com.example.ztiproj.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping
    @Operation(hidden = true)
    public ResponseEntity<Void> registerUser(@RequestBody AuthenticationDto authenticationDto) {
        userService.registerUser(authenticationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
