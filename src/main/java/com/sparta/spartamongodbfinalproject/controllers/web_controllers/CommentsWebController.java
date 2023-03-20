package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.controllers.StringFormatter;
import com.sparta.spartamongodbfinalproject.model.entities.Comments;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsWebController {

    private final CommentsRepository commentsRepository;

    public CommentsWebController(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @GetMapping("/comments")
    public String commentsHome(){
        return "comments-page";
    }

    @GetMapping("/comments/search")
    public String getCommentSearchParameter(){
        return "comments/comment-search-form";
    }

    @GetMapping("/comments/results")
    public String getCommentSearchResults(Model model, @RequestParam String name){
        model.addAttribute("comments", commentsRepository.findCommentsByNameEquals(StringFormatter.capitalizeWord(name)));
        return "comments/comment-search-results";
    }

    @GetMapping("comments/create")
    public String getCommentCreateForm(){
        return "comments/comment-create-form";
    }

    @PostMapping("/createcomment")
    public String createComment(@ModelAttribute Comments comment){
        return "comments/comment-create-success";
    }

}
