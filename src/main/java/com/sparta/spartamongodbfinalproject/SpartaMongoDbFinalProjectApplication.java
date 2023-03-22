package com.sparta.spartamongodbfinalproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpartaMongoDbFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaMongoDbFinalProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MovieRepository movieRepository, ObjectMapper objectMapper) {
        return args -> {
            Movie movie = movieRepository.findByTitle("Blacksmith Scene");
            System.out.println(objectMapper.writeValueAsString(movie));
        };
    }

}
