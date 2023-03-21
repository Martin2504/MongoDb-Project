package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;
import com.sparta.spartamongodbfinalproject.model.entities.CommentCreator;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class CommentsWebController {

    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;

    public CommentsWebController(CommentRepository commentRepository,
                                 MovieRepository movieRepository) {
        this.commentRepository = commentRepository;
        this.movieRepository = movieRepository;
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
        model.addAttribute("comments", commentRepository.findCommentByNameEquals(name));
        return "comments/comment-search-results";
    }

    @GetMapping("/comments/create")
    public String getCommentCreateForm(){
        return "comments/comment-create-form";
    }

    @PostMapping("/createcomment")
    public String createComment(@ModelAttribute("commentToCreate") CommentCreator commentToCreate){
        SpartaMongoDbFinalProjectApplication.logger.info(commentToCreate.toString());
        Comment comment = new Comment();
        comment.setName(commentToCreate.getName());
        comment.setEmail(commentToCreate.getEmail());
        SpartaMongoDbFinalProjectApplication.logger.info(commentToCreate.getMovieTitle());
        comment.setMovie(movieRepository.findMovieByTitleEquals(commentToCreate.getMovieTitle()));
        SpartaMongoDbFinalProjectApplication.logger.info(comment.getMovie().toString());
        commentToCreate.setDate(LocalDateTime.now().toString());
        comment.setDate(commentToCreate.getDate());
        comment.setText(commentToCreate.getText());
        SpartaMongoDbFinalProjectApplication.logger.info(comment.toString());
        commentRepository.save(comment);
        return "comments/comment-create-success";
    }

    @GetMapping("/comments/edit/{commentId}")
    public String getCommentEditForm(@PathVariable String commentId, Model model){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        model.addAttribute("commentToEdit", comment);
        return "comments/comment-edit-form";
    }

    @PostMapping("/editcomment")
    public String editComment(@ModelAttribute("commentToEdit") Comment comment){
        SpartaMongoDbFinalProjectApplication.logger.info(comment.toString());
        commentRepository.save(comment);
        return "comments/comment-edit-success";
    }
    @GetMapping("/comments/delete/{commentId}")
    public String deleteComment(@PathVariable String commentId){
        SpartaMongoDbFinalProjectApplication.logger.info(commentId);
        commentRepository.deleteById(commentId);
        return "comments/comment-delete-success";
    }
}
