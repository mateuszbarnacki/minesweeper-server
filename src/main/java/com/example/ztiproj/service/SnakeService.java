package com.example.ztiproj.service;

import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.mapper.SnakeMapper;
import com.example.ztiproj.repository.SnakeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SnakeService {
    private final SnakeRepository repository;
    private final SnakeMapper mapper;

    public List<SnakeDto> getRanking() {
        return repository.getTopScores()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<SnakeDto> getUserRanking(String userName) {
        return repository.getTopUserScores(userName)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Optional<SnakeDto> addResult(SnakeDto snakeDto) {
        return Optional.ofNullable(snakeDto)
                .map(mapper::map)
                .map(repository::insert)
                .map(mapper::map);
    }

    public void deleteAllUserResults(String userName) {
        repository.deleteByUserName(userName);
    }
}
