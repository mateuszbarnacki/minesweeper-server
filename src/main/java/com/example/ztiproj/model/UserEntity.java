package com.example.ztiproj.model;

import com.example.ztiproj.common.Labels;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class UserEntity {
    @Id
    private String id;

    @Field(name = Labels.USER_NAME)
    private String name;

    @Indexed(name = Labels.USER_ENTITY_INDEX_NAME, unique = true, background = true)
    @Field(name = Labels.USER_USERNAME)
    private String userName;

    @Field(name = Labels.USER_PASSWORD)
    private String password;

    public UserEntity(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

}
