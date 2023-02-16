package com.example.ztiproj.minesweeper.mapper;

import com.example.ztiproj.minesweeper.dto.MinesweeperDto;
import com.example.ztiproj.minesweeper.model.Minesweeper;
import com.example.ztiproj.minesweeper.service.MinesweeperMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-27
 */
@SpringBootTest
class MinesweeperMapperTest {

    @Autowired
    private MinesweeperMapper minesweeperMapper;

    @Test
    void shouldMapEntityToDto() {
        Minesweeper entity = new Minesweeper();
        entity.setUserName("Matib");
        entity.setTime(777L);

        MinesweeperDto dto = this.minesweeperMapper.map(entity);

        assertThat(dto).hasFieldOrPropertyWithValue("userName", "Matib")
                .hasFieldOrPropertyWithValue("time", 777L);
    }

    @Test
    void shouldMapDtoToEntity() {
        MinesweeperDto dto = new MinesweeperDto("matib", 253L);

        Minesweeper entity = this.minesweeperMapper.map(dto);

        assertThat(entity).hasFieldOrPropertyWithValue("userName", "matib")
                .hasFieldOrPropertyWithValue("time", 253L);
    }
}
