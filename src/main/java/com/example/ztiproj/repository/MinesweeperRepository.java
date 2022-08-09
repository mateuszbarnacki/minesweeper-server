package com.example.ztiproj.repository;

import com.example.ztiproj.model.MinesweeperEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinesweeperRepository extends MongoRepository<MinesweeperEntity, Long> {

    @Aggregation("{}, {'limit': 10}")
    List<MinesweeperEntity> getTopScores();

    @Aggregation("{'username': ?1}, {'limit': 10}")
    List<MinesweeperEntity> getTopUserScores(String userName);

    @Query(value = "{'username' : ?0}", delete = true)
    void deleteByUserName(String userName);

}
