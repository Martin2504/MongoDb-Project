package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.controllers.StringFormatter;
import com.sparta.spartamongodbfinalproject.model.entities.Comments;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentsRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MoviesRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class CommentsWebController {

    private final CommentsRepository commentsRepository;
    private final MoviesRepository moviesRepository;

    public CommentsWebController(CommentsRepository commentsRepository,
                                 MoviesRepository moviesRepository) {
        this.commentsRepository = commentsRepository;
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/comments")
    public String commentsHome(){
        return "mainPages/comments-page";
    }

    @GetMapping("/comments/search")
    public String getCommentSearchParameter(){
        return "comments/comment-search-form";
    }

    @GetMapping("/comments/results")
    public String getCommentSearchResults(Model model, @RequestParam String name){
        model.addAttribute("comments", commentsRepository.findCommentsByNameEquals(name));
        return "comments/comment-search-results";
    }

    @GetMapping("/comments/create")
    public String getCommentCreateForm(){
        return "comments/comment-create-form";
    }

    @PostMapping("/createcomment")
    public String createComment(@ModelAttribute("commentToCreate") Comments comment){
        comment.setDate(LocalDateTime.now());
        comment.setMovie_id(moviesRepository.findMoviesByTitleEquals());
        SpartaMongoDbFinalProjectApplication.logger.info(comment.toString());
        return "comments/comment-create-success";
    }

    @GetMapping("/comments/edit/{commentId}")
    public String getCommentEditForm(@PathVariable ObjectId commentId, Model model){
        Comments comment = commentsRepository.findById(commentId).orElse(null);
        model.addAttribute("commentToEdit", comment);
        return "comments/comment-edit-form";
    }

    @PostMapping("editcomment")
    public String editComment(@ModelAttribute("commentToEdit") Comments comment){
        SpartaMongoDbFinalProjectApplication.logger.info(comment.toString());
        return "comments/comment-edit-success";
    }


}
