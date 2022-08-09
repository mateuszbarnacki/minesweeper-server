package com.example.ztiproj.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@NonNull
public class MinesweeperDto {
    String userName;
    Long time;
}
