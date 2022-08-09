package com.example.ztiproj.repository;

import com.example.ztiproj.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Long> {

    UserEntity getByUserName(String userName);

}
