package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;

import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class CommentsRestController {

    private CommentRepository commentRepository;

    private MovieRepository movieRepository;
    ObjectMapper objectMapper;

    @Autowired
    public CommentsRestController(CommentRepository commentRepository, MovieRepository movieRepository,
                                  ObjectMapper objectMapper){
        this.commentRepository = commentRepository;
        this.movieRepository = movieRepository;
        this.objectMapper = objectMapper;
    }

    /*
        HTTP FORMAT FOR createComment:
        ... localhost:8080/api/comments/post?comment=newComment&name=martin
        &email=martin%40gmail.com&movie_title=Traffic%20in%20Souls&runtime=88 ...
    */
    @PostMapping("api/comments/post")
    public ResponseEntity<String> createComment(@RequestParam String comment,
                                                @RequestParam String name,
                                                @RequestParam String email,
                             @RequestParam String movie_id ,
                                                @RequestParam LocalDateTime date

    ){
        ObjectMapper objectMapper = new ObjectMapper();
        Comment createdComment = new Comment();
        //SpartaMongoDbFinalProjectApplication.logger.info(comment);
        createdComment.setText(comment);
        //SpartaMongoDbFinalProjectApplication.logger.info(name);
        createdComment.setName(name);
        //SpartaMongoDbFinalProjectApplication.logger.info(email);
        createdComment.setEmail(email);
        //SpartaMongoDbFinalProjectApplication.logger.info(movie_title);
        createdComment.setMovie(movieRepository.findMovieByTitleAndRuntime(movie_title, runtime));
        createdComment.setDate(LocalDateTime.now());

        commentRepository.save(createdComment);

        return ResponseEntity.ok("Comment: "  + comment + " has been posted");

    }

    @GetMapping("/api/comments/{cid}")
    public ResponseEntity<String> getCommentById(@PathVariable("cid") String id) {
        System.out.println("Reaching this method");
        Optional<Comment> foundComment = commentRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(foundComment.isPresent()){
            try {
                ResponseEntity<String> response = new ResponseEntity<>(objectMapper.writeValueAsString(foundComment.get()), httpHeaders, HttpStatus.OK);
                return response;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity<String> commentNotFoundResponse = new ResponseEntity<>(
                "{\"message\";\"That comment doesn't exist\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND);
        return commentNotFoundResponse;


    }

    @PatchMapping("api/comments/{uid}")
    public ResponseEntity<String> updateComment(
            @PathVariable("uid") String id,
            @RequestParam String text,
            @RequestParam LocalDateTime date

    ) {

        Optional<Comment> comments = commentRepository.findById(id);
        comments.get().setText("edited" + text);
        comments.get().setDate(LocalDateTime.now());
        commentRepository.save(comments.get());

        return ResponseEntity.ok("Comment: "  + comments.get().getText() + "has been updated");
    }


    @DeleteMapping("api/comments/{did}")
    public ResponseEntity<String> deleteComment(@PathVariable("did") String id){

        Optional<Comment> comments = commentRepository.findById(id);
//        commentRepository.delete(comments.get());
        return ResponseEntity.ok("Comment has been deleted");

    }



}
