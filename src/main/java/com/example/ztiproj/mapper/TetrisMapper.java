package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.TetrisDto;
import com.example.ztiproj.model.Tetris;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Component
@NoArgsConstructor
public class TetrisMapper {
    public Tetris map(TetrisDto dto) {
        return new Tetris(dto.getUserName(), dto.getScore());
    }

    public TetrisDto map(Tetris entity) {
        return TetrisDto.builder()
                .userName(entity.getUserName())
                .score(entity.getScore())
                .build();
    }
}
