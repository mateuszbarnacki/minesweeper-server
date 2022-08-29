package com.example.ztiproj.repository;

import com.example.ztiproj.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-09
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity getByUserName(String username);

}
