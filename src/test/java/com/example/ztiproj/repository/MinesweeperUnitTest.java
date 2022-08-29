package com.example.ztiproj.repository;

import com.example.ztiproj.model.MinesweeperEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class MinesweeperUnitTest {
    @Autowired
    private MinesweeperRepository repository;

    @Before
    public void setUp() {
        repository.save(new MinesweeperEntity("Abacki", 10L));
        repository.save(new MinesweeperEntity("Abacki", 52L));
        repository.save(new MinesweeperEntity("Babacki", 32L));
        repository.save(new MinesweeperEntity("Abacki", 26L));
        repository.save(new MinesweeperEntity("Abacki", 24L));
        repository.save(new MinesweeperEntity("Abacki", 14L));
        repository.save(new MinesweeperEntity("Cabacki", 15L));
        repository.save(new MinesweeperEntity("Dadacki", 33L));
        repository.save(new MinesweeperEntity("Abacki", 21L));
        repository.save(new MinesweeperEntity("Abacki", 17L));
        repository.save(new MinesweeperEntity("Babacki", 28L));
        repository.save(new MinesweeperEntity("Abacki", 13L));
        repository.save(new MinesweeperEntity("Abacki", 24L));
        repository.save(new MinesweeperEntity("Abacki", 20L));
        repository.save(new MinesweeperEntity("Abacki", 16L));
        repository.save(new MinesweeperEntity("Abacki", 9L));
    }

    @Test
    public void givenTopRankRequest_thenReturnsTop10Results() {
        List<MinesweeperEntity> topRanking = repository.getTopScores();
        Assertions.assertEquals(10, topRanking.size());
    }

    @Test
    public void givenTopRankRequest_thenBestResultUserNameIsCorrect() {
        MinesweeperEntity bestResult = repository.getTopScores()
                .stream()
                .min(Comparator.comparing(MinesweeperEntity::getTime))
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals("Abacki", bestResult.getUserName());
    }

    @Test
    public void givenTopRankRequest_thenWorstResultTimeIsCorrect() {
        MinesweeperEntity worstResult = repository.getTopScores()
                .stream()
                .max(Comparator.comparing(MinesweeperEntity::getTime))
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(24L, worstResult.getTime());
    }

    @Test
    public void givenUserTopRankRequest_thenListContainsOnlyRequestedUserResults() {
        String userName = "Abacki";
        List<MinesweeperEntity> topUserRanking = repository.getTopUserScores(userName)
                .stream()
                .filter(result -> !result.getUserName().equals(userName))
                .collect(Collectors.toList());
        Assertions.assertTrue(topUserRanking.isEmpty());
    }

    @Test
    public void givenUserTopRankRequest_thenListContainsTop10UserResults() {
        List<MinesweeperEntity> topUserRanking = repository.getTopUserScores("Abacki");
        Assertions.assertEquals(10, topUserRanking.size());
    }

    @Test
    public void givenUserTopRankRequest_thenBestUserTimeIsCorrect() {
        MinesweeperEntity bestResult = repository.getTopUserScores("Abacki")
                .stream()
                .min(Comparator.comparing(MinesweeperEntity::getTime))
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(9L, bestResult.getTime());
    }

    @Test
    public void givenUserTopRankRequest_thenWorstUserTimeIsCorrect() {
        MinesweeperEntity worstResult = repository.getTopUserScores("Abacki")
                .stream()
                .max(Comparator.comparing(MinesweeperEntity::getTime))
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(24L, worstResult.getTime());
    }

    @Test
    public void givenUserName_whenDeleteAllUserResults_thenRankingHasCorrectSize() {
        String userName = "Abacki";
        long expectedResult = repository.findAll().stream()
                .filter(result -> !result.getUserName().equals(userName))
                .count();
        repository.deleteByUserName(userName);
        long actualResult = repository.count();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @After
    public void tearDown() {
        repository.deleteByUserName("Abacki");
        repository.deleteByUserName("Babacki");
        repository.deleteByUserName("Cabacki");
        repository.deleteByUserName("Dadacki");
    }
}
