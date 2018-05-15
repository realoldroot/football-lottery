package com.artemis.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballLotteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballLotteryApplication.class, args);
    }
}
