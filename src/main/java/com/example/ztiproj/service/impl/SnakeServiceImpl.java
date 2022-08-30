package com.example.ztiproj.service.impl;

import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.exception.InvalidSnakeResultException;
import com.example.ztiproj.mapper.SnakeMapper;
import com.example.ztiproj.repository.SnakeRepository;
import com.example.ztiproj.service.api.SnakeService;
import com.example.ztiproj.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-11
 */
@Service
@AllArgsConstructor
public class SnakeServiceImpl implements SnakeService {
    private final SnakeRepository repository;
    private final UserService userService;
    private final SnakeMapper mapper;

    public List<SnakeDto> getRanking() {
        return repository.getTopScores()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<SnakeDto> getUserRanking(String userName) {
        userService.checkUser(userName);
        return repository.getTopUserScores(userName)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public SnakeDto addResult(SnakeDto snakeDto) {
        return Optional.ofNullable(snakeDto)
                .map(mapper::map)
                .map(repository::insert)
                .map(mapper::map)
                .orElseThrow(InvalidSnakeResultException::new);
    }

    public void deleteAllUserResults(String userName) {
        userService.checkUser(userName);
        repository.deleteByUserName(userName);
    }
}
