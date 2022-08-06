package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.SnakeDto;
import com.example.ztiproj.model.SnakeEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SnakeMapper {

    public SnakeEntity map(SnakeDto dto) {
        return new SnakeEntity(dto.getScore());
    }

    public SnakeDto map(SnakeEntity entity) {
        return SnakeDto.builder()
                .score(entity.getScore())
                .build();
    }
}
