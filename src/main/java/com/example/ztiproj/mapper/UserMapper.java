package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.UserDto;
import com.example.ztiproj.model.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {

    public UserEntity map(final UserDto dto) {
        return new UserEntity(dto.getUserName(), dto.getPassword());
    }

    public UserDto map(final UserEntity entity) {
        return UserDto.builder()
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .build();
    }
}
