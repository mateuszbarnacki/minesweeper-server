package com.example.ztiproj.service.impl;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.dto.UserDto;
import com.example.ztiproj.exception.NonExistentUserException;
import com.example.ztiproj.mapper.UserMapper;
import com.example.ztiproj.model.UserEntity;
import com.example.ztiproj.repository.UserRepository;
import com.example.ztiproj.service.api.UserService;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
                .orElseThrow(() -> new NonExistentUserException(buildExceptionMessage(username)));
    }

    public UserDto addUser(UserDto userDto) {
        return Optional.of(userDto)
                .map(mapper::map)
                .map(repository::save)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException(Labels.INVALID_USER_DTO_EXCEPTION_MESSAGE));
    }

    public void checkUser(String userName) {
        if (Strings.isNullOrEmpty(userName)) {
            throw new NonExistentUserException(Labels.EMPTY_USERNAME_EXCEPTION_MESSAGE);
        }
        UserEntity user = repository.getByUserName(userName);
        if (Objects.isNull(user)) {
            throw new NonExistentUserException(buildExceptionMessage(userName));
        }
    }

    private String buildExceptionMessage(String username) {
        return String.format("Could not find user with %s username", username);
    }
}
