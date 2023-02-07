package com.example.ztiproj.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-27
 */
@SpringBootTest
class MinesweeperDtoTest {
    private MinesweeperDto minesweeperDto;

    @BeforeEach
    public void setup() {
        this.minesweeperDto = MinesweeperDto.builder()
                .userName("matib")
                .time(134L)
                .build();
    }

    @Test
    void shouldContainUserNameAndTime() {
        Assertions.assertThat(minesweeperDto)
                .hasFieldOrPropertyWithValue("userName", "matib")
                .hasFieldOrPropertyWithValue("time", 134L);
    }
}
