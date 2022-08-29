package com.example.ztiproj.service;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.mapper.MinesweeperMapper;
import com.example.ztiproj.repository.MinesweeperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MinesweeperService {
    private final MinesweeperRepository repository;
    private final MinesweeperMapper mapper;

    public List<MinesweeperDto> getRanking() {
        return repository.getTopScores()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<MinesweeperDto> getUserRanking(String userName) {
        return repository.getTopUserScores(userName)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public MinesweeperDto addResult(MinesweeperDto minesweeperDto) {
        return Optional.ofNullable(minesweeperDto)
                .map(mapper::map)
                .map(repository::save)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Invalid minesweeper dto!"));
    }

    public void deleteAllUserResults(String userName) {
        repository.deleteByUserName(userName);
    }
}
