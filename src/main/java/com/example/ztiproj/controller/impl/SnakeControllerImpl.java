package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.SnakeController;
import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.service.SnakeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        return service.addResult(dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Invalid object!"));
    }

    @Override
    public void deleteAllUserResults(String username) {
        service.deleteAllUserResults(username);
    }
}
