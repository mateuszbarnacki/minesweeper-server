package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.model.MinesweeperEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MinesweeperMapper {

    public MinesweeperEntity map(MinesweeperDto dto) {
        return new MinesweeperEntity(dto.getTime());
    }

    public MinesweeperDto map(MinesweeperEntity entity) {
        return MinesweeperDto.builder()
                .time(entity.getTime())
                .build();
    }
}
