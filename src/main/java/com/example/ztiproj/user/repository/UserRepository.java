package com.example.ztiproj.user.repository;

import com.example.ztiproj.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-16
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'username':?0}")
    User findUserByUsername(String username);
}
