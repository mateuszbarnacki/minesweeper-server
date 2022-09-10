package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-06
 */
@Document(value = Labels.MINESWEEPER_ENTITY)
@Getter
@Setter
@ToString
public class Minesweeper {
    @Id
    private String id;

    @Indexed(name = Labels.MINESWEEPER_ENTITY_INDEX_NAME, background = true)
    @Field(name = Labels.MINESWEEPER_USERNAME)
    private String userName;

    @Field(name = Labels.MINESWEEPER_TIME)
    private Long time;

    public Minesweeper() {
    }

    public Minesweeper(String userName, Long time) {
        this.userName = userName;
        this.time = time;
    }

}
