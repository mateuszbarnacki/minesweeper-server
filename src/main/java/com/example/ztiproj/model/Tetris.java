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
@Document(value = Labels.TETRIS_ENTITY)
@Getter
@Setter
@ToString
public class Tetris {
    @Id
    private String id;

    @Indexed(name = Labels.TETRIS_ENTITY_INDEX_NAME, background = true)
    @Field(name = Labels.TETRIS_USERNAME)
    private String userName;

    @Field(name = Labels.TETRIS_SCORE)
    private Long score;

    public Tetris(String userName, Long score) {
        this.userName = userName;
        this.score = score;
    }

}
