package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = Labels.TETRIS_ENTITY)
public class TetrisEntity {
    @Id
    private Long id;
    @Indexed(name = Labels.TETRIS_ENTITY_INDEX_NAME, background = true)
    private String userName;
    private Long score;

    public TetrisEntity() {
    }

    public TetrisEntity(String userName, Long score) {
        this.userName = userName;
        this.score = score;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getScore() {
        return this.score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
