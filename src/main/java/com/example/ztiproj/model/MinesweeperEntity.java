package com.example.ztiproj.model;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class MinesweeperEntity {
    @Id
    private Long id;
    @Column
    private Long time;

    public MinesweeperEntity() {
    }

    public MinesweeperEntity(Long time) {
        this.time = time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
