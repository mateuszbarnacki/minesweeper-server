package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.MinesweeperController;
import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.exception.InvalidMinesweeperResultException;
import com.example.ztiproj.service.api.MinesweeperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-09
 */
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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addResult(dto));
        } catch (NullPointerException e) {
            throw new InvalidMinesweeperResultException("Null dto provided!");
        }
    }

    @Override
    public void deleteAllUserResults(String username) {
        service.deleteAllUserResults(username);
    }
}
