package com.example.ztiproj.service;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.mapper.MinesweeperMapper;
import com.example.ztiproj.model.Minesweeper;
import com.example.ztiproj.repository.MinesweeperRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-11
 */
@Service
public class RestMinesweeperService implements MinesweeperService {
    private final MinesweeperRepository repository;
    private final MinesweeperMapper mapper;

    public RestMinesweeperService(MinesweeperRepository repository, MinesweeperMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Collection<MinesweeperDto> getRanking() {
        return repository.getTopScores()
                .stream()
                .map(mapper::map)
                .toList();
    }

    public Collection<MinesweeperDto> getUserRanking(String userName) {
        return repository.getTopUserScores(userName)
                .stream()
                .map(mapper::map)
                .toList();
    }

    public MinesweeperDto addResult(@NotNull MinesweeperDto minesweeperDto) {
        Minesweeper result = mapper.map(minesweeperDto);
        Minesweeper saved = repository.save(result);
        return mapper.map(saved);
    }

    public void deleteAllUserResults(String userName) {
        repository.deleteByUserName(userName);
    }
}
