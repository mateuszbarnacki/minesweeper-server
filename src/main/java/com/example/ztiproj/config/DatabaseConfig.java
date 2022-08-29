package com.example.ztiproj.config;

import com.example.ztiproj.repository.MinesweeperRepository;
import com.example.ztiproj.repository.SnakeRepository;
import com.example.ztiproj.repository.TetrisRepository;
import com.example.ztiproj.repository.UserRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Mateusz Barnacki
 * @version 1.0
 * @since 2022-08-11
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = {MinesweeperRepository.class, TetrisRepository.class,
        SnakeRepository.class, UserRepository.class})
public class DatabaseConfig extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "pz";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create();
    }
}
