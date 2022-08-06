package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.model.TetrisEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TetrisMapper {

    public TetrisEntity map(TetrisDto dto) {
        return new TetrisEntity(dto.getScore());
    }

    public TetrisDto map(TetrisEntity entity) {
        return TetrisDto.builder()
                .score(entity.getScore())
                .build();
    }
}
