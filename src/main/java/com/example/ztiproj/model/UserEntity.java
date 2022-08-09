package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = Labels.USER_ENTITY)
public class UserEntity {
    @Id
    private Long id;
    @Indexed(name = Labels.USER_ENTITY_INDEX_NAME, unique = true, background = true)
    private String userName;
    private String password;

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
}
