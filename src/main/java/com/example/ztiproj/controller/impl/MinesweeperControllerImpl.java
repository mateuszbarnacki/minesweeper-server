package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.MinesweeperController;
import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.service.MinesweeperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MinesweeperControllerImpl implements MinesweeperController {
    private final MinesweeperService service;

    @Override
    public ResponseEntity<List<MinesweeperDto>> getRanking() {
        return ResponseEntity.ok(service.getRanking());
    }

    @Override
    public ResponseEntity<List<MinesweeperDto>> getUserRanking(String username) {
        return ResponseEntity.ok(service.getUserRanking(username));
    }

    @Override
    public ResponseEntity<MinesweeperDto> addResult(MinesweeperDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addResult(dto));
    }

    @Override
    public void deleteAllUserResults(String username) {
        service.deleteAllUserResults(username);
    }
}
