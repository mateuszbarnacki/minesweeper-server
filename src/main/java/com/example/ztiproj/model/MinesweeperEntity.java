package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = Labels.MINESWEEPER_ENTITY)
public class MinesweeperEntity {
    @Id
    private Long id;
    @Indexed(name = Labels.MINESWEEPER_ENTITY_INDEX_NAME, background = true)
    private String userName;
    private Long time;

    public MinesweeperEntity() {
    }

    public MinesweeperEntity(String userName, Long time) {
        this.userName = userName;
        this.time = time;
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

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
