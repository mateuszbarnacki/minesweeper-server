package com.example.ztiproj.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Value
@Builder
@NonNull
public class UserDto {
    String name;
    String userName;
    String password;
}
