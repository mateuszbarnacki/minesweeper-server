package com.example.ztiproj.service;

import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.mapper.TetrisMapper;
import com.example.ztiproj.repository.TetrisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TetrisService {
    private final TetrisRepository repository;
    private final TetrisMapper mapper;

    public List<TetrisDto> getRanking() {
        return repository.getTopScores()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<TetrisDto> getUserRanking(String userName) {
        return repository.getTopUserScores(userName)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Optional<TetrisDto> addResult(TetrisDto tetrisDto) {
        return Optional.ofNullable(tetrisDto)
                .map(mapper::map)
                .map(repository::insert)
                .map(mapper::map);
    }

    public void deleteAllUserResults(String userName) {
        repository.deleteByUserName(userName);
    }
}
