package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity(name = Labels.USER_ENTITY)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_minesweeper",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "minesweeper_id"))
    private Set<MinesweeperEntity> minesweeperEntities;
    @ManyToMany
    @JoinTable(
            name = "user_tetris",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tetris_id"))
    private Set<TetrisEntity> tetrisEntities;
    @ManyToMany
    @JoinTable(
            name = "user_snake",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "snake_id"))
    private Set<SnakeEntity>snakeEntities;

    public UserEntity() {}

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MinesweeperEntity> getMinesweeperEntities() {
        return this.minesweeperEntities;
    }

    public void setMinesweeperEntities(Set<MinesweeperEntity> minesweeperEntities) {
        this.minesweeperEntities = minesweeperEntities;
    }

    public Set<TetrisEntity> getTetrisEntities() {
        return this.tetrisEntities;
    }

    public void setTetrisEntities(Set<TetrisEntity> tetrisEntities) {
        this.tetrisEntities = tetrisEntities;
    }

    public Set<SnakeEntity> getSnakeEntities() {
        return this.snakeEntities;
    }

    public void setSnakeEntities(Set<SnakeEntity> snakeEntities) {
        this.snakeEntities = snakeEntities;
    }
}
