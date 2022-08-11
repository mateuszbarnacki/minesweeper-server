package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.model.TetrisEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TetrisMapper {
    public TetrisEntity map(TetrisDto dto) {
        return new TetrisEntity(dto.getUserName(), dto.getScore());
    }

    public TetrisDto map(TetrisEntity entity) {
        return TetrisDto.builder()
                .userName(entity.getUserName())
                .score(entity.getScore())
                .build();
    }
}
