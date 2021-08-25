package com.itechart.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringprojectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringprojectApplication.class, args);
    }
}
