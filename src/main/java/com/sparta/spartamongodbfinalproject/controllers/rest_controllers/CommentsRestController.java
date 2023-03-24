package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;

import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

                                                @RequestParam String movie_title,
                                                @RequestParam Integer runtime

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
//        createdComment.setMovie(movieRepository.findMovieByTitleAndRuntime(movie_title, runtime));
        createdComment.setDate(LocalDateTime.now());
        commentRepository.save(createdComment);

        return ResponseEntity.ok("Comment: \""  + comment + "\", has been posted");

    }


    // ... localhost:8080/api/comments/5a9427648b0beebeb69579e7 ...
    @GetMapping("/api/comments/{cid}")
    public ResponseEntity<String> getCommentById(@PathVariable("cid") String id) {
        //SpartaMongoDbFinalProjectApplication.logger.info("Reaching this method");
        Optional<Comment> foundComment = commentRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(foundComment.isPresent()){
            try {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Comment Author", foundComment.get().getName());
                map.put("Movie Title", foundComment.get().getMovie().getTitle());
                map.put("Comment Text", foundComment.get().getText());
                ResponseEntity<String> response = new ResponseEntity<>(objectMapper.writeValueAsString(map),
                         httpHeaders, HttpStatus.OK);
                return response;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity<String> commentNotFoundResponse = new ResponseEntity<>(
                "{\"message\":\"That comment doesn't exist\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND);
        return commentNotFoundResponse;
    }

    // ... localhost:8080/api/comments/5a9427648b0beebeb69579e7?text=Changing%20this%20comment%20text%20again ...
    @PatchMapping("api/comments/{uid}")
    public ResponseEntity<String> updateComment(
            @PathVariable("uid") String id,

            @RequestParam String text,
            @RequestParam LocalDateTime date


    ) {

        Optional<Comment> comments = commentRepository.findById(id);
        String temp = comments.get().getText();
        comments.get().setText("edited: " + text);
        comments.get().setDate(LocalDateTime.now());
        SpartaMongoDbFinalProjectApplication.logger.info(comments.get().toString());
        commentRepository.save(comments.get());

        return ResponseEntity.ok("Comment: \""  + temp + "\", has been updated to: \"" + comments.get().getText() + "\"" );
    }

//    @PatchMapping("api/comments/title/{uc}")
//    public ResponseEntity<String> updateCommentByFilm(
//            @PathVariable("uc") String movieTitle,
//            @RequestParam String text
//    ) {
//
//        Optional<Movie> movie = Optional.ofNullable(movieRepository.findByTitle(movieTitle));
//        SpartaMongoDbFinalProjectApplication.logger.info(movie.get().toString());
//        Optional<Comment> comments = commentRepository.findById(movie.get().getId());
//        String temp = comments.get().getText();
//        comments.get().setText("edited: " + text);
//        comments.get().setDate(LocalDateTime.now());
//        commentRepository.save(comments.get());
//
//        return ResponseEntity.ok("Comment: \""  + temp + "\", has been updated to: \"" + comments.get().getText() + "\"");
//    }


    @DeleteMapping("api/comments/{did}")
    public ResponseEntity<String> deleteComment(@PathVariable("did") String id){

        Optional<Comment> comments = commentRepository.findById(id);
        commentRepository.delete(comments.get());
        return ResponseEntity.ok("Comment has been deleted");

    }



}
