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
@Document(value = Labels.SNAKE_ENTITY)
@Getter
@Setter
@ToString
public class Snake {
    @Id
    private String id;

    @Indexed(name = Labels.SNAKE_ENTITY_INDEX_NAME, background = true)
    @Field(name = Labels.SNAKE_USERNAME)
    private String userName;

    @Field(name = Labels.SNAKE_SCORE)
    private Long score;

    public Snake(String userName, Long score) {
        this.userName = userName;
        this.score = score;
    }

}
