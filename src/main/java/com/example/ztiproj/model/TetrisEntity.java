package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity(name = Labels.TETRIS_ENTITY)
public class TetrisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long score;
    @ManyToMany(mappedBy = "tetrisEntities")
    private Set<UserEntity> userEntities;

    public TetrisEntity() {
    }

    public TetrisEntity(Long score) {
        this.score = score;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScore() {
        return this.score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Set<UserEntity> getUserEntities() {
        return this.userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}
