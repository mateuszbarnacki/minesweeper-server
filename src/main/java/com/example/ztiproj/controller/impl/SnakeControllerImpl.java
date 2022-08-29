package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.SnakeController;
import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.service.api.SnakeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-11
 */
@AllArgsConstructor
public class SnakeControllerImpl implements SnakeController {
    private final SnakeService service;

    @Override
    public ResponseEntity<List<SnakeDto>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @Override
    public ResponseEntity<List<SnakeDto>> getUserRanking(String username) {
        return ResponseEntity.ok(service.getUserRanking(username));
    }

    @Override
    public ResponseEntity<SnakeDto> addResult(SnakeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addResult(dto));
    }

    @Override
    public void deleteAllUserResults(String username) {
        service.deleteAllUserResults(username);
    }
}
