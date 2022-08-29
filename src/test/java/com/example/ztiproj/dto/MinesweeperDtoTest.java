package com.example.ztiproj.dto;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class MinesweeperDtoTest {
    private MinesweeperDto minesweeperDto;

    @Before
    public void setup() {
        this.minesweeperDto = MinesweeperDto.builder()
                .userName("matib")
                .time("00:04:13")
                .build();
    }

    @Test
    public void shouldContainUserNameAndTime() {
        Assertions.assertThat(minesweeperDto)
                .hasFieldOrPropertyWithValue("userName", "matib")
                .hasFieldOrPropertyWithValue("time", "00:04:13");
    }
}
