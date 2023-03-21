package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;

import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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


    @PostMapping("api/comments/{_id}")
    public ResponseEntity<String> createComment(@PathVariable ObjectId _id,
                                                @RequestParam String comment,
                                                @RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String  movie_id ,
                                                @RequestParam String date
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
        Optional<Comment> comments = commentRepository.findById(id);

        try {
            objectMapper.writeValueAsString(comments.get());
            return ResponseEntity.ok(objectMapper.toString());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }

    }

    @PatchMapping("api/comments/{uid}")
    public ResponseEntity<String> updateComment(
            @PathVariable("uid") String id,
            @RequestParam String text,
            @RequestParam String date
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
