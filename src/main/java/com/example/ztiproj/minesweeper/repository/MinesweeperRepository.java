package com.example.ztiproj.minesweeper.repository;

import com.example.ztiproj.minesweeper.common.Labels;
import com.example.ztiproj.minesweeper.model.Minesweeper;
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
public interface MinesweeperRepository extends MongoRepository<Minesweeper, String> {

    @Aggregation(pipeline = {
            "{'$sort': {'" + Labels.MINESWEEPER_TIME + "': 1}}",
            "{'$limit': 10}"
    })
    List<Minesweeper> getTopScores();

    @Aggregation(pipeline = {
            "{'$match':{'" + Labels.MINESWEEPER_USERNAME + "': ?0}}",
            "{'$sort': {'" + Labels.MINESWEEPER_TIME + "': 1}}",
            "{'$limit': 10}"
    })
    List<Minesweeper> getTopUserScores(String userName);

    @Query(value = "{'" + Labels.MINESWEEPER_USERNAME + "' : ?0}", delete = true)
    void deleteByUserName(String userName);

}
