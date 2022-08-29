package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.UserDto;
import com.example.ztiproj.model.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Component
@NoArgsConstructor
public class UserMapper {
    public UserEntity map(final UserDto dto) {
        return new UserEntity(dto.getName(), dto.getUserName(), dto.getPassword());
    }

    public UserDto map(final UserEntity entity) {
        return UserDto.builder()
                .name(entity.getName())
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .build();
    }
}
