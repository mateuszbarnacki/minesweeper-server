package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.model.Minesweeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class MinesweeperMapperTest {

    @Autowired
    private MinesweeperMapper minesweeperMapper;

    @Test
    public void shouldMapEntityToDto() {
        Minesweeper entity = new Minesweeper();
        entity.setUserName("Matib");
        entity.setTime(777L);

        MinesweeperDto dto = this.minesweeperMapper.map(entity);

        assertThat(dto).hasFieldOrPropertyWithValue("userName", "Matib")
                .hasFieldOrPropertyWithValue("time", 777L);
    }

    @Test
    public void shouldMapDtoToEntity() {
        MinesweeperDto dto = MinesweeperDto.builder()
                .userName("matib")
                .time(253L)
                .build();

        Minesweeper entity = this.minesweeperMapper.map(dto);

        assertThat(entity).hasFieldOrPropertyWithValue("userName", "matib")
                .hasFieldOrPropertyWithValue("time", 253L);
    }
}
