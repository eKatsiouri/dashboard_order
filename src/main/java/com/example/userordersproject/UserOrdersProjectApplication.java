package com.example.userordersproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableScheduling
public class UserOrdersProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserOrdersProjectApplication.class, args);
    }

}
