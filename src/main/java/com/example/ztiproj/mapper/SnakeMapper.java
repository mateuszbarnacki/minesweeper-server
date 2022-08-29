package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.model.SnakeEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Component
@NoArgsConstructor
public class SnakeMapper {
    public SnakeEntity map(SnakeDto dto) {
        return new SnakeEntity(dto.getUserName(), dto.getScore());
    }

    public SnakeDto map(SnakeEntity entity) {
        return SnakeDto.builder()
                .userName(entity.getUserName())
                .score(entity.getScore())
                .build();
    }
}
