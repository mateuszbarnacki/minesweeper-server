package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
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
public class MinesweeperEntity {
    @Id
    private String id;

    @Indexed(name = Labels.MINESWEEPER_ENTITY_INDEX_NAME, background = true)
    @Field(name = "username")
    private String userName;

    @Field(name = "time")
    private Long time;

    public MinesweeperEntity() {
    }

    public MinesweeperEntity(String userName, Long time) {
        this.userName = userName;
        this.time = time;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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
