package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = Labels.USER_ENTITY)
public class UserEntity {
    @Id
    private String id;

    @Indexed(name = Labels.USER_ENTITY_INDEX_NAME, unique = true, background = true)
    @Field(name = "username")
    private String userName;

    @Field(name = "password")
    private String password;

    public UserEntity() {}

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
