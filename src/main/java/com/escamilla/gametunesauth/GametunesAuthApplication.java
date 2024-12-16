package com.escamilla.gametunesauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = {"com.escamilla.gametunesauth"}
        , exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})

public class GametunesAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametunesAuthApplication.class, args);
    }

}
