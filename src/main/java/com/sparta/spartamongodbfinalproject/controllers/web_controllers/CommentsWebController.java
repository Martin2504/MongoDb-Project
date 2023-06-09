package com.sparta.spartamongodbfinalproject.controllers.web_controllers;

import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
import com.sparta.spartamongodbfinalproject.logicalOperator.Success;
import com.sparta.spartamongodbfinalproject.model.entities.Comment;
import com.sparta.spartamongodbfinalproject.model.entities.CommentCreator;
import com.sparta.spartamongodbfinalproject.model.entities.CommentMovieSearcher;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web")
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

    @GetMapping("/comments/search-by-name")
    public String getCommentSearchByNameParameter(){
        return "comments/comment-search-by-name-form";
    }

    @GetMapping("/comments/search-by-movie")
    public String getCommentSearchByMovieParameter(){
        return "comments/comment-search-by-movie-form";
    }

    @GetMapping("/comments/results-by-name")
    public String getCommentSearchByNameResults(Model model, @RequestParam String name){
        model.addAttribute("comments", commentRepository.findCommentByNameEquals(name));
        return "comments/comment-search-by-name-results";
    }

    @GetMapping("/comments/results-by-movie")
    public String getCommentSearchByMovieResults(Model model, @RequestParam String title){
        List<Movie> movies = movieRepository.findMovieByTitleEquals(title);
        List<CommentMovieSearcher> commentSearch = new ArrayList<>();
        if(movies.isEmpty()){
            return "comments/movie-does-not-exist";
        }else {
            for(Movie movie : movies) {
                CommentMovieSearcher currentMovie = new CommentMovieSearcher();
                currentMovie.setMovie(movie);
                currentMovie.setComments(commentRepository.findCommentByMovie_Id(movie.getId()));
                commentSearch.add(currentMovie);
                model.addAttribute("commentSearches", commentSearch);
            }
            return "comments/comment-search-results";
        }
    }

    @GetMapping("/comments/create")
    public String getCommentCreateForm(){
        return "comments/comment-create-form";
    }

    @PostMapping("/createcomment")
    public String createComment(@ModelAttribute("commentToCreate") CommentCreator commentToCreate, Model model){
        SpartaMongoDbFinalProjectApplication.logger.info(commentToCreate.toString());
        Comment comment = new Comment();
        comment.setName(commentToCreate.getName());
        comment.setEmail(commentToCreate.getEmail());
        SpartaMongoDbFinalProjectApplication.logger.info(commentToCreate.getMovieTitle());
        SpartaMongoDbFinalProjectApplication.logger.info(commentToCreate.toString());
        Movie movie = movieRepository.findMovieByTitleEquals(commentToCreate.getMovieTitle()).get(0);
        comment.setMovie(movie);
        commentToCreate.setDate(LocalDateTime.now());
        comment.setDate(commentToCreate.getDate());
        comment.setText(commentToCreate.getText());
        SpartaMongoDbFinalProjectApplication.logger.info(comment.toString());
        if(comment.getMovie() == null){
            return "comments/movie-does-not-exist";
        }else {
            commentRepository.save(comment);
            SpartaMongoDbFinalProjectApplication.logger.info(comment.getMovie().toString());

            Success success=new Success("Create", "Comment");
            model.addAttribute("success",success);
            return "fragments/success";
        }
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
