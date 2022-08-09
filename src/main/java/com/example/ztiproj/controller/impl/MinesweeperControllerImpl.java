package com.example.ztiproj.controller.impl;

import com.example.ztiproj.controller.api.MinesweeperController;
import com.example.ztiproj.dto.MinesweeperDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class MinesweeperControllerImpl implements MinesweeperController {
    //private MinesweeperService minesweeperService;

    @Override
    public ResponseEntity<MinesweeperDto> getRanking() {
        return null;
    }

    @Override
    public ResponseEntity<MinesweeperDto> getUserRanking(String username) {
        return null;
    }

    @Override
    public ResponseEntity<MinesweeperDto> addResult(MinesweeperDto dto) {
        return null;
    }

    @Override
    public void deleteAllUserResults(String username) {

    }
}
