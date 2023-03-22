package com.sparta.spartamongodbfinalproject;

import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpartaMongoDbFinalProjectApplication {

    private final CommentRepository commentRepository;

    public static final Logger logger = LoggerFactory.getLogger(SpartaMongoDbFinalProjectApplication.class);

    public SpartaMongoDbFinalProjectApplication(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpartaMongoDbFinalProjectApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner runner(CommentsRepository commentsRepository){
//        return args -> logger.info(commentsRepository.findCommentsByNameEquals("John Bishop").toString());
//    }
}
