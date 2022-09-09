package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.model.Minesweeper;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Component
@NoArgsConstructor
public class MinesweeperMapper {
    public Minesweeper map(MinesweeperDto dto) {
        return new Minesweeper(dto.getUserName(), dto.getTime());
    }

    public MinesweeperDto map(Minesweeper entity) {
        return MinesweeperDto.builder()
                .userName(entity.getUserName())
                .time(entity.getTime())
                .build();
    }
}
