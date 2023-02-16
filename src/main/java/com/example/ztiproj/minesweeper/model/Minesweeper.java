package com.example.ztiproj.minesweeper.model;

import com.example.ztiproj.minesweeper.common.Labels;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Minesweeper(String userName, Long time) {
        this.userName = userName;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Minesweeper{" +
                "userName='" + userName + '\'' +
                ", time=" + time +
                '}';
    }
}
