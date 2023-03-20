package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;
import com.sparta.spartamongodbfinalproject.model.entities.Comments;

import com.sparta.spartamongodbfinalproject.model.repositories.CommentsRepository;
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

    private CommentsRepository commentsRepository;

    @Autowired
    public CommentsRestController(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }


    @PostMapping("api/comments/{_id}")
    public ResponseEntity<String> createComment(@PathVariable ObjectId _id,
                                                @RequestParam String comment,
                                                @RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String  movie_id ,
                                                @RequestParam String text,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime date
    ){
        Comments createdComment = new Comments();
        createdComment.set_id(_id);
        createdComment.setText(comment);
        createdComment.setName(name);
        createdComment.setEmail(email);
        createdComment.setMovie_id(movie_id);
        createdComment.setDate(date);

        commentsRepository.save(createdComment);

        return ResponseEntity.ok("Comment: "  + comment + " has been posted");

    }

    @GetMapping("/api/comments/{cid}")
    public ResponseEntity<String> getCommentById(@PathVariable("cid") ObjectId id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        return comments.map(value -> ResponseEntity.ok(value.getText())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found"));
    }

    @PatchMapping("api/comments/{uid}")
    public ResponseEntity<String> updateComment(
            @PathVariable("uid") ObjectId id,
            @RequestParam String text,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime date
    ) {

        Optional<Comments> comments = commentsRepository.findById(id);
        comments.get().setText("edited" + text);
        comments.get().setDate(date);
        commentsRepository.save(comments.get());

        return ResponseEntity.ok("Comment: "  + comments.get().getText() + "has been updated");
    }


    @DeleteMapping("api/comments/{did}")
    public ResponseEntity<String> deleteComment(@PathVariable("did") ObjectId id){

        Optional<Comments> comments = commentsRepository.findById(id);
        commentsRepository.delete(comments.get());
        return ResponseEntity.ok("Comment has been deleted");

    }



}
