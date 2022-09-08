package com.example.ztiproj.repository;

import com.example.ztiproj.model.Tetris;
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

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-09-08
 */
@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class TetrisIntegrationTest {
    @Autowired
    private TetrisRepository repository;

    @Before
    public void setUp() {
        repository.save(new Tetris("Abacki", 12523L));
        repository.save(new Tetris("Abacki", 5436L));
        repository.save(new Tetris("Abacki", 3456L));
        repository.save(new Tetris("Abacki", 3125L));
        repository.save(new Tetris("Babacki", 2893L));
        repository.save(new Tetris("Abacki", 2364L));
        repository.save(new Tetris("Babacki", 2094L));
        repository.save(new Tetris("Abacki", 2023L));
        repository.save(new Tetris("Abacki", 1843L));
        repository.save(new Tetris("Babacki", 1664L));
        repository.save(new Tetris("Abacki", 1423L));
        repository.save(new Tetris("Abacki", 1331L));
        repository.save(new Tetris("Cabacki", 1255L));
        repository.save(new Tetris("Abacki", 1235L));
        repository.save(new Tetris("Abacki", 1111L));
    }

    @Test
    public void givenTopRankRequest_thenReturns10Results() {
        long actual = repository.getTopScores().size();
        Assertions.assertEquals(10, actual);
    }

    @Test
    public void givenTopRankRequest_thenBestResultUserNameIsCorrect() {
        String expectedUserName = repository.findAll().stream()
                .sorted(Comparator.comparing(Tetris::getScore).reversed())
                .reduce((first, second) -> first)
                .map(Tetris::getUserName)
                .orElseThrow(RuntimeException::new);
        String actualUserName = repository.getTopScores().stream()
                .reduce((first, second) -> first)
                .map(Tetris::getUserName)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(expectedUserName, actualUserName);
    }

    @Test
    public void givenTopRankRequest_thenWorstResultScoreIsCorrect() {
        long expectedScore = repository.findAll().stream()
                .sorted(Comparator.comparing(Tetris::getScore).reversed())
                .limit(10)
                .reduce((first, second) -> second)
                .map(Tetris::getScore)
                .orElseThrow(RuntimeException::new);
        long actualScore = repository.getTopScores().stream()
                .reduce((first, second) -> second)
                .map(Tetris::getScore)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(expectedScore, actualScore);
    }

    @Test
    public void givenTopUserRankRequest_thenRankHasCorrectSize() {
        String userName = "Babacki";
        long expected = repository.findAll().stream()
                .filter(tetris -> tetris.getUserName().equals(userName))
                .count();
        long actual = repository.getTopUserScores(userName).size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTopUserRankRequest_thenResultLimitIs10() {
        long actual = repository.getTopUserScores("Abacki").size();
        Assertions.assertEquals(10, actual);
    }

    @Test
    public void givenTopUserRankRequest_thenBestUserResultIsCorrect() {
        String userName = "Abacki";
        long expected = repository.findAll().stream()
                .filter(tetris -> tetris.getUserName().equals(userName))
                .max(Comparator.comparing(Tetris::getScore))
                .map(Tetris::getScore)
                .orElseThrow(RuntimeException::new);
        long actual = repository.getTopUserScores(userName).stream()
                .reduce((first, second) -> first)
                .map(Tetris::getScore)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTopUserRankRequest_thenWorstResultUserNameIsCorrect() {
        String userName = "Abacki";
        String expected = repository.findAll().stream()
                .filter(tetris -> tetris.getUserName().equals(userName))
                .min(Comparator.comparing(Tetris::getScore))
                .map(Tetris::getUserName)
                .orElseThrow(RuntimeException::new);
        String actual = repository.getTopUserScores(userName).stream()
                .reduce((first, second) -> second)
                .map(Tetris::getUserName)
                .orElseThrow(RuntimeException::new);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenUserName_whenDeleteAllUserResults_thenRankingSizeIsCorrect() {
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
    }
}
