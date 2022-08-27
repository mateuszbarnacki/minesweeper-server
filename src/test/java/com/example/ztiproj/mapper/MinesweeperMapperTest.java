package com.example.ztiproj.mapper;

import com.example.ztiproj.dto.MinesweeperDto;
import com.example.ztiproj.model.MinesweeperEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class MinesweeperMapperTest {

    @Autowired
    private MinesweeperMapper minesweeperMapper;

    @Test
    public void shouldMapEntityToDto() {
        MinesweeperEntity entity = new MinesweeperEntity();
        entity.setUserName("Matib");
        entity.setTime(777L);

        MinesweeperDto dto = this.minesweeperMapper.map(entity);

        assertThat(dto).hasFieldOrPropertyWithValue("userName", "Matib")
                .hasFieldOrPropertyWithValue("time", "00:12:57");
    }

    @Test
    public void shouldMapDtoToEntity() {
        MinesweeperDto dto = MinesweeperDto.builder()
                .userName("matib")
                .time("00:04:13")
                .build();

        MinesweeperEntity entity = this.minesweeperMapper.map(dto);

        assertThat(entity).hasFieldOrPropertyWithValue("userName", "matib")
                .hasFieldOrPropertyWithValue("time", 253L);
    }
}
