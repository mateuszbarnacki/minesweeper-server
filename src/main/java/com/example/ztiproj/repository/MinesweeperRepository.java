package com.example.ztiproj.repository;

import com.example.ztiproj.model.MinesweeperEntity;
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
public interface MinesweeperRepository extends MongoRepository<MinesweeperEntity, String> {

    @Aggregation(pipeline = {
            "{'$sort': {'time': 1}}",
            "{'$limit': 10}"
    })
    List<MinesweeperEntity> getTopScores();

    @Aggregation(pipeline = {
            "{'$match':{'username': ?0}}",
            "{'$sort': {'time': 1}}",
            "{'$limit': 10}"
    })
    List<MinesweeperEntity> getTopUserScores(String userName);

    @Query(value = "{'username' : ?0}", delete = true)
    void deleteByUserName(String userName);

}
