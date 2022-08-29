package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.TetrisController;
import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.service.api.TetrisService;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addResult(dto));
    }

    @Override
    public void deleteAllUserResults(String username) {
        service.deleteAllUserResults(username);
    }
}
