package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.TetrisController;
import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.service.TetrisService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
public class TetrisControllerImpl implements TetrisController {
    private final TetrisService service;

    @Override
    public ResponseEntity<List<TetrisDto>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @Override
    public ResponseEntity<List<TetrisDto>> getUserRanking(String username) {
        return ResponseEntity.ok(service.getUserRanking(username));
    }

    @Override
    public ResponseEntity<TetrisDto> addResult(TetrisDto dto) {
        return service.addResult(dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Invalid object!"));
    }

    @Override
    public void deleteAllUserResults(String username) {
        service.deleteAllUserResults(username);
    }
}
