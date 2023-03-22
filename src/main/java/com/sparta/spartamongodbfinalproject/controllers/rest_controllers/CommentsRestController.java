package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;

import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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


    @PostMapping("api/comments/{id}/")
    public ResponseEntity<String> createComment(@PathVariable String id,
                                                @RequestParam String comment,
                                                @RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String movie_id ,
                                                @RequestParam LocalDateTime date
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        Comment createdComment = new Comment();

        createdComment.setText(comment);
        createdComment.setName(name);
        createdComment.setEmail(email);
        createdComment.setMovie(movieRepository.findById(movie_id).get());
        createdComment.setDate(date);

        commentRepository.save(createdComment);

        return ResponseEntity.ok("Comment: "  + comment + " has been posted");

    }

    @GetMapping("/api/comments/{cid}")
    public ResponseEntity<String> getCommentById(@PathVariable("cid") String id) {
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

    public ResponseEntity<String> getCommentByMovie(Model model, @RequestParam String title) {
        Movie movie = movieRepository.findMovieByTitleEquals(title).orElse(null);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        ResponseEntity<String> response = null;

        if (movie == null) {
            response = new ResponseEntity<>(
                    "{\"message\";\"That movie doesn't exist\"}",
                    httpHeaders,
                    HttpStatus.NOT_FOUND);
        } else {
            List<Comment> comments = commentRepository.findCommentByMovie_Id(movie.getId());
            response = null;

            try {
                response = new ResponseEntity<>(objectMapper.writeValueAsString(comments), httpHeaders, HttpStatus.OK);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return response;
    }

    @PatchMapping("api/comments/{uid}")
    public ResponseEntity<String> updateComment(
            @PathVariable("uid") String id,
            @RequestParam String text,
            @RequestParam LocalDateTime date
    ) {

        Optional<Comment> comments = commentRepository.findById(id);
        comments.get().setText("edited" + text);
        comments.get().setDate(date);
        commentRepository.save(comments.get());

        return ResponseEntity.ok("Comment: "  + comments.get().getText() + "has been updated");
    }


    @DeleteMapping("api/comments/{did}")
    public ResponseEntity<String> deleteComment(@PathVariable("did") String id){

        Optional<Comment> comments = commentRepository.findById(id);
        commentRepository.delete(comments.get());
        return ResponseEntity.ok("Comment has been deleted");

    }

}