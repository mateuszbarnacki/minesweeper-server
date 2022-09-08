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
public class SnakeDto {
    String userName;
    Long score;

    @ConstructorProperties({"userName", "score"})
    public SnakeDto(String userName, Long score) {
        this.userName = userName;
        this.score = score;
    }
}
