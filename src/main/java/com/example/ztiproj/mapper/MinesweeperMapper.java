package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.model.MinesweeperEntity;
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
    public MinesweeperEntity map(MinesweeperDto dto) {
        Long time = mapDtoToEntityTime(dto.getTime());
        return new MinesweeperEntity(dto.getUserName(), time);
    }

    public MinesweeperDto map(MinesweeperEntity entity) {
        String time = mapEntityToDtoTime(entity.getTime());
        return MinesweeperDto.builder()
                .userName(entity.getUserName())
                .time(time)
                .build();
    }

    private Long mapDtoToEntityTime(String time) {
        String[] timeElements = time.split(":");
        int multiplier = 1;
        long timeInSeconds = 0L;
        for (int i = 2; i >= 0; i--) {
            timeInSeconds += (Long.parseLong(timeElements[i]) * multiplier);
            multiplier *= 60;
        }
        return timeInSeconds;
    }

    private String mapEntityToDtoTime(long time) {
        String seconds = String.valueOf(time % 60);
        String minutes = String.valueOf(time / 60);
        String hours = String.valueOf(time / 3600);

        if (hours.length() == 1) {
            hours = "0" + hours;
        }

        return hours + ":" + minutes + ":" + seconds;
    }
}
