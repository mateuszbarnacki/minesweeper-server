package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.model.Minesweeper;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Component
public class MinesweeperMapper {
    public Minesweeper map(MinesweeperDto dto) {
        return new Minesweeper(dto.userName(), dto.time());
    }

    public MinesweeperDto map(Minesweeper entity) {
        return new MinesweeperDto(entity.getUserName(), entity.getTime());
    }
}
