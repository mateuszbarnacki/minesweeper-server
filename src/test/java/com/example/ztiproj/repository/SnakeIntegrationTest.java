package com.example.ztiproj.repository;

import com.example.ztiproj.model.Snake;
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

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-09-08
 */
@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class SnakeIntegrationTest {
    @Autowired
    private SnakeRepository repository;

    @Before
    public void setUp() {
        repository.save(new Snake("Cabacki", 52L));
        repository.save(new Snake("Dadacki", 33L));
        repository.save(new Snake("Babacki", 32L));
        repository.save(new Snake("Babacki", 28L));
        repository.save(new Snake("Abacki", 26L));
        repository.save(new Snake("Abacki", 24L));
        repository.save(new Snake("Babacki", 24L));
        repository.save(new Snake("Abacki", 21L));
        repository.save(new Snake("Abacki", 20L));
        repository.save(new Snake("Abacki", 17L));
        repository.save(new Snake("Abacki", 16L));
        repository.save(new Snake("Cabacki", 15L));
        repository.save(new Snake("Abacki", 14L));
        repository.save(new Snake("Abacki", 13L));
        repository.save(new Snake("Abacki", 10L));
        repository.save(new Snake("Abacki", 9L));
    }

    @Test
    public void givenTopRankRequest_thenReturns10Results() {
        List<Snake> ranking = repository.getTopScores();
        Assertions.assertEquals(10, ranking.size());
    }

    @Test
    public void givenTopRankRequest_thenBestResultScoreIsCorrect() {
        Snake bestResult = repository.getTopScores().stream()
                .reduce((first, second) -> first)
                .orElseThrow(RuntimeException::new);
        AssertionsForClassTypes.assertThat(bestResult)
                .hasFieldOrPropertyWithValue("userName", "Cabacki")
                .hasFieldOrPropertyWithValue("score", 52L);
    }

    @Test
    public void givenTopRankRequest_thenWorstResultUserNameIsCorrect() {
        Snake worstResult = repository.getTopScores().stream()
                .reduce((first, second) -> second)
                .orElseThrow(RuntimeException::new);
        AssertionsForClassTypes.assertThat(worstResult)
                .hasFieldOrPropertyWithValue("userName", "Abacki")
                .hasFieldOrPropertyWithValue("score", 17L);
    }

    @Test
    public void givenUserTopRankRequest_thenRankHasCorrectSize() {
        List<Snake> ranking = repository.getTopUserScores("Babacki");
        Assertions.assertEquals(3, ranking.size());
    }

    @Test
    public void givenUserTopRankRequest_thenBestUserResultIsCorrect() {
        long actual = repository.getTopUserScores("Dadacki").stream()
                .reduce((first, second) -> first)
                .map(Snake::getScore)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(33L, actual);
    }

    @Test
    public void givenUserTopRankRequest_thenRankHasTop10Results() {
        List<Snake> ranking = repository.getTopUserScores("Abacki");
        Assertions.assertEquals(10, ranking.size());
    }

    @Test
    public void givenUserName_whenDeleteUser_thenRankingHasCorrectSize() {
        String userName = "Abacki";
        long expected = repository.findAll().stream()
                .filter(result -> !result.getUserName().equals(userName))
                .count();
        repository.deleteByUserName(userName);
        long actual = repository.count();
        Assertions.assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        repository.deleteByUserName("Abacki");
        repository.deleteByUserName("Babacki");
        repository.deleteByUserName("Cabacki");
        repository.deleteByUserName("Dadacki");
    }
}
