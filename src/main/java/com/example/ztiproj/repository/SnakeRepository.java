package com.example.ztiproj.repository;

import com.example.ztiproj.model.SnakeEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnakeRepository extends MongoRepository<SnakeEntity, Long> {

    @Aggregation("{}, {'limit': 10}")
    List<SnakeEntity> getTopScores();

    @Aggregation("{'username': ?1}, {'limit': 10}")
    List<SnakeEntity> getTopUserScores(String userName);

    @Query(value = "{'username' : ?0}", delete = true)
    void deleteByUserName(String userName);

}
