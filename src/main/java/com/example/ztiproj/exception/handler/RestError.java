package com.example.ztiproj.exception.handler;

import lombok.Builder;
import lombok.Data;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
@Data
@Builder
public class RestError {
    private String message;
    private int code;
}
