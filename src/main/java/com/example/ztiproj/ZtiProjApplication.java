package com.example.ztiproj;

import com.example.ztiproj.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ZtiProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZtiProjApplication.class, args);
    }
}