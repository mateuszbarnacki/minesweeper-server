package com.example.ztiproj.service.impl;

import com.example.ztiproj.dto.UserDto;
import com.example.ztiproj.mapper.UserMapper;
import com.example.ztiproj.repository.UserRepository;
import com.example.ztiproj.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-29
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto getUserByUserName(String username) {
        return Optional.ofNullable(repository.getByUserName(username))
                .map(mapper::map)
                .orElseThrow(() -> new NoSuchElementException(String.format("Could not find user with %s username", username)));
    }

    public UserDto addUser(UserDto userDto) {
        return Optional.of(userDto)
                .map(mapper::map)
                .map(repository::save)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user dto"));
    }
}
