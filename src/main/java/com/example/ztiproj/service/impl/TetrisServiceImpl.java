package com.example.ztiproj.service.impl;

import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.exception.InvalidTetrisResultException;
import com.example.ztiproj.mapper.TetrisMapper;
import com.example.ztiproj.repository.TetrisRepository;
import com.example.ztiproj.service.api.TetrisService;
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
public class TetrisServiceImpl implements TetrisService {
    private final TetrisRepository repository;
    private final UserService userService;
    private final TetrisMapper mapper;

    public List<TetrisDto> getRanking() {
        return repository.getTopScores()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<TetrisDto> getUserRanking(String userName) {
        userService.checkUser(userName);
        return repository.getTopUserScores(userName)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public TetrisDto addResult(TetrisDto tetrisDto) {
        return Optional.ofNullable(tetrisDto)
                .map(mapper::map)
                .map(repository::insert)
                .map(mapper::map)
                .orElseThrow(InvalidTetrisResultException::new);
    }

    public void deleteAllUserResults(String userName) {
        userService.checkUser(userName);
        repository.deleteByUserName(userName);
    }
}
