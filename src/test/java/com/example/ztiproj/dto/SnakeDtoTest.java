package com.example.ztiproj.dto;

import org.assertj.core.api.AssertionsForClassTypes;
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
 * @since 2022-09-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class SnakeDtoTest {
    private SnakeDto dto;

    @Before
    public void setUp() {
        this.dto = SnakeDto.builder()
                .score(13L)
                .userName("matib")
                .build();
    }

    @Test
    public void shouldContainScoreAndUserNameFields() {
        AssertionsForClassTypes.assertThat(dto)
                .hasFieldOrPropertyWithValue("userName", "matib")
                .hasFieldOrPropertyWithValue("score", 13L);
    }

}
