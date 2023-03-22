package com.sparta.spartamongodbfinalproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpartaMongoDbFinalProjectApplication {
    public static final Logger logger = LoggerFactory.getLogger(SpartaMongoDbFinalProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpartaMongoDbFinalProjectApplication.class, args);
    }



}
