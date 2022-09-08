package com.example.ztiproj.repository;

import com.example.ztiproj.common.Labels;
import com.example.ztiproj.model.Tetris;
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
public interface TetrisRepository extends MongoRepository<Tetris, String> {

    @Aggregation(pipeline = {
            "{'$sort': {'" + Labels.TETRIS_SCORE + "': 1}}",
            "{'$limit': 10}"
    })
    List<Tetris> getTopScores();

    @Aggregation(pipeline = {
            "{'$match':{'" + Labels.TETRIS_USERNAME + "': ?0}}",
            "{'$sort': {'" + Labels.TETRIS_SCORE + "': 1}}",
            "{'$limit': 10}"
    })
    List<Tetris> getTopUserScores(String userName);

    @Query(value = "{'" + Labels.TETRIS_USERNAME + "' : ?0}", delete = true)
    void deleteByUserName(String userName);

}
