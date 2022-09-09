package com.example.ztiproj.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.beans.ConstructorProperties;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Value
@Builder
@NonNull
public class MinesweeperDto {
    String userName;
    Long time;

    @ConstructorProperties({"userName", "time"})
    public MinesweeperDto(String userName, Long time) {
        this.userName = userName;
        this.time = time;
    }
}
