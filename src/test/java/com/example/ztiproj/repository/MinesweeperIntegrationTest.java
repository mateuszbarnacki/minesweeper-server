package com.example.ztiproj.repository;

import com.example.ztiproj.model.Minesweeper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-27
 */
@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class MinesweeperIntegrationTest {
    @Autowired
    private MinesweeperRepository repository;

    @Before
    public void setUp() {
        repository.save(new Minesweeper("Abacki", 10L));
        repository.save(new Minesweeper("Abacki", 52L));
        repository.save(new Minesweeper("Babacki", 32L));
        repository.save(new Minesweeper("Abacki", 26L));
        repository.save(new Minesweeper("Abacki", 24L));
        repository.save(new Minesweeper("Abacki", 14L));
        repository.save(new Minesweeper("Cabacki", 15L));
        repository.save(new Minesweeper("Dadacki", 33L));
        repository.save(new Minesweeper("Abacki", 21L));
        repository.save(new Minesweeper("Abacki", 17L));
        repository.save(new Minesweeper("Babacki", 28L));
        repository.save(new Minesweeper("Abacki", 13L));
        repository.save(new Minesweeper("Abacki", 24L));
        repository.save(new Minesweeper("Abacki", 20L));
        repository.save(new Minesweeper("Abacki", 16L));
        repository.save(new Minesweeper("Abacki", 9L));
    }

    @Test
    public void givenTopRankRequest_thenReturns10Results() {
        List<Minesweeper> topRanking = repository.getTopScores();
        Assertions.assertEquals(10, topRanking.size());
    }

    @Test
    public void givenTopRankRequest_thenBestResultUserNameIsCorrect() {
        Minesweeper bestResult = repository.getTopScores()
                .stream()
                .reduce((first, second) -> first)
                .orElseThrow(RuntimeException::new);
        AssertionsForClassTypes.assertThat(bestResult)
                .hasFieldOrPropertyWithValue("userName", "Abacki")
                .hasFieldOrPropertyWithValue("time", 9L);
    }

    @Test
    public void givenTopRankRequest_thenWorstResultTimeIsCorrect() {
        Minesweeper worstResult = repository.getTopScores()
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(RuntimeException::new);
        AssertionsForClassTypes.assertThat(worstResult)
                .hasFieldOrPropertyWithValue("userName", "Abacki")
                .hasFieldOrPropertyWithValue("time", 24L);
    }

    @Test
    public void givenUserTopRankRequest_thenListContainsOnlyRequestedUserResults() {
        String userName = "Abacki";
        List<Minesweeper> topUserRanking = repository.getTopUserScores(userName)
                .stream()
                .filter(result -> !result.getUserName().equals(userName))
                .collect(Collectors.toList());
        Assertions.assertTrue(topUserRanking.isEmpty());
    }

    @Test
    public void givenUserTopRankRequest_thenListContainsTop10UserResults() {
        List<Minesweeper> topUserRanking = repository.getTopUserScores("Abacki");
        Assertions.assertEquals(10, topUserRanking.size());
    }

    @Test
    public void givenUserTopRankRequest_thenBestUserTimeIsCorrect() {
        long actual = repository.getTopUserScores("Abacki")
                .stream()
                .reduce((first, second) -> first)
                .map(Minesweeper::getTime)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(9L, actual);
    }

    @Test
    public void givenUserTopRankRequest_thenWorstUserTimeIsCorrect() {
        long actual = repository.getTopUserScores("Abacki")
                .stream()
                .reduce((first, second) -> second)
                .map(Minesweeper::getTime)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(24L, actual);
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
