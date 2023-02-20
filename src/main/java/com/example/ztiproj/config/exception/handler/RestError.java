package com.example.ztiproj.config.exception.handler;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-30
 */
public record RestError(@NotBlank String message, ErrorCode status) {
}
