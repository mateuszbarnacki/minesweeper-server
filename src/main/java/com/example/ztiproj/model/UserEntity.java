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
@Document(value = Labels.USER_ENTITY)
public class UserEntity {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Indexed(name = Labels.USER_ENTITY_INDEX_NAME, unique = true, background = true)
    @Field(name = "username")
    private String userName;

    @Field(name = "password")
    private String password;

    public UserEntity() {
    }

    public UserEntity(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
