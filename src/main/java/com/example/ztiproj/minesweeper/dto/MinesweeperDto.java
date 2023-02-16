package com.example.ztiproj.minesweeper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
public record MinesweeperDto(@NotBlank String userName, @NotNull Long time) {
}
