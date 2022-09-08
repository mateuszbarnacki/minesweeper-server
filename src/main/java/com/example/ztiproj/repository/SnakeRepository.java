package com.example.ztiproj.repository;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.model.Snake;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-09
 */
@Repository
public interface SnakeRepository extends MongoRepository<Snake, String> {

    @Aggregation(pipeline = {
            "{'$sort': {'" + Labels.SNAKE_SCORE + "': -1}}",
            "{'$limit': 10}"
    })
    List<Snake> getTopScores();

    @Aggregation(pipeline = {
            "{'$match':{'" + Labels.SNAKE_USERNAME + "': ?0}}",
            "{'$sort': {'" + Labels.SNAKE_SCORE + "': -1}}",
            "{'$limit': 10}"
    })
    List<Snake> getTopUserScores(String userName);

    @Query(value = "{'" + Labels.SNAKE_USERNAME + "' : ?0}", delete = true)
    void deleteByUserName(String userName);

}
