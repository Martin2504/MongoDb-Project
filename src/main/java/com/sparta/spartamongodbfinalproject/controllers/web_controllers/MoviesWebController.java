package com.sparta.spartamongodbfinalproject.controllers.web_controllers;


import com.sparta.spartamongodbfinalproject.SpartaMongoDbFinalProjectApplication;
//import com.sparta.spartamongodbfinalproject.model.entities.Movie;

import com.sparta.spartamongodbfinalproject.logicalOperator.Success;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;

import com.sparta.spartamongodbfinalproject.logicalOperator.StringToArrayString;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.movies.*;

import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller()

@RequestMapping("/web")
public class MoviesWebController {
    private MovieRepository movieRepository;
    private Tomato tomato;
    private CommentRepository commentRepository;
    @Autowired
    public MoviesWebController(MovieRepository movieRepository, CommentRepository commentRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
    }



    //find all
    @GetMapping("/movies")
    public String getAllMovies(Model model){

        SpartaMongoDbFinalProjectApplication.logger.info("before");

        List<Movie> movieList = movieRepository.findAll(PageRequest.of(1, 2)).toList();

        SpartaMongoDbFinalProjectApplication.logger.info(movieList.toString());
        model.addAttribute("movies",movieList);

        return "movies/movies";
    }


//    //create
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/movie/create")
    public String createMovie(Model model) {
        return "movies/movie-add-form";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute("movieToCreate") Movie addedMovie, Model model,
//
                              Award awards,
                              Imdb imdb,
                              Tomato tomato,
                              Viewer viewer,
                              Critic critic
                              ) {
        Integer numOfComments=commentRepository.findCommentByMovie_Id(addedMovie.getId()).size();
        addedMovie.setNum_mflix_comments(numOfComments);

        LocalDateTime releaseDate=addedMovie.getReleased();
        if (releaseDate!=null){
            addedMovie.setYear(addedMovie.getReleased().getYear());
        }
        tomato.setViewer(viewer);
        tomato.setCritic(critic);
        tomato.setTomato_lastUpdated(LocalDateTime.now());
        tomato.setTomato_lastUpdated(LocalDateTime.now());
        addedMovie.setTomatoes(tomato);
        addedMovie.setAwards(awards);
        addedMovie.setImdb(imdb);
        addedMovie.setLastupdated(LocalDateTime.now().toString());
//        SpartaMongoDbFinalProjectApplication.logger.info(addedMovie.toString());
//        SpartaMongoDbFinalProjectApplication.logger.info(addedMovie.getId());
        movieRepository.save(addedMovie);

        Success success=new Success("Create", "Movie");
        model.addAttribute("success",success);
        return "fragments/success";
    }


    //Read
//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/movie/find")
    public String findMovie() {
        return "movies/movie-find-form";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/findMovieById")
    public String findMovieById(@ModelAttribute("movieToFind")Movie foundMovie,Model model) {
        SpartaMongoDbFinalProjectApplication.logger.info(foundMovie.toString());
        String id=foundMovie.getId();
        List<Movie> movies;
        if(id!=null){
            Movie movie=movieRepository.findMoviesById(id);
            movies=new ArrayList<Movie>();
            SpartaMongoDbFinalProjectApplication.logger.info(movie.toString());
            if (movie != null) {
                movies.add(movie);
                model.addAttribute("movies",movies);
            }
        } else {
            model.addAttribute("movies",null);

        }
        return "movies/movie";
    }

    @PostMapping("/findByMovieTitle")
    public String findMovieByTitle(@ModelAttribute("movieToFind")Movie foundMovie,Model model) {
        String title=foundMovie.getTitle();
        SpartaMongoDbFinalProjectApplication.logger.info(title);
        List<Movie> foundMovies;
    if(!title.equals("")){
            foundMovies=movieRepository.findMoviesByTitle(title);
        SpartaMongoDbFinalProjectApplication.logger.info(foundMovies.toString());
        }
        else {
        foundMovies=new ArrayList<Movie>();
        }
        SpartaMongoDbFinalProjectApplication.logger.info(String.valueOf(foundMovies.size()));
        if(foundMovies.size()==0){
            model.addAttribute("movies",null);
        } else {
            model.addAttribute("movies",foundMovies);
        }
        return "movies/movie";
    }

    @GetMapping("/movie/delete/{id}")
    public String deleteMovie(@PathVariable String id) {
        movieRepository.deleteById(id);
        return "movies/success";
    }


    @GetMapping("/movie/edit/{id}")
    public String movieToEdit(Model model, @PathVariable String id) {
        Movie movie = movieRepository.findMoviesById(id);
        this.tomato=movie.getTomatoes();
        SpartaMongoDbFinalProjectApplication.logger.info("this.tomato: "+this.tomato.toString());
        model.addAttribute("movieToEdit", movie);
        return "/movies/movie-edit-form";
    }



    @PostMapping("/editMovie")
    public String editMovie(@ModelAttribute("movieToEdit") Movie editedMovie) {
//        SpartaMongoDbFinalProjectApplication.logger.info("0: "+editedMovie.toString());
        Tomato editedTomato=editedMovie.getTomatoes();
        editedTomato.setTomato_lastUpdated(this.tomato.getTomato_lastUpdated());
        //if there is change made to tomato
        if(this.tomato==null && editedTomato!=null ){
            editedTomato.setTomato_lastUpdated(LocalDateTime.now());
//            SpartaMongoDbFinalProjectApplication.logger.info("editedTomato: "+editedTomato.toString());
//            SpartaMongoDbFinalProjectApplication.logger.info("setLastUpdateTomato called 1");
        } else if(this.tomato!=null&& editedTomato!=null
                && !this.tomato.toString().equals(editedTomato.toString())){
//            SpartaMongoDbFinalProjectApplication.logger.info(this.tomato.toString());
//            SpartaMongoDbFinalProjectApplication.logger.info(editedTomato.toString());
//            SpartaMongoDbFinalProjectApplication.logger.info("setLastUpdateTomato called 2");

            editedTomato.setTomato_lastUpdated(LocalDateTime.now());
        }
//        SpartaMongoDbFinalProjectApplication.logger.info("1: "+editedMovie.toString());
        LocalDateTime releaseDate=editedMovie.getReleased();
        if (releaseDate!=null){
            editedMovie.setYear(editedMovie.getReleased().getYear());
        }
        Integer numOfComments=commentRepository.findCommentByMovie_Id(editedMovie.getId()).size();
        editedMovie.setNum_mflix_comments(numOfComments);
//        SpartaMongoDbFinalProjectApplication.logger.info("3: "+editedMovie.getNum_mflix_comments().toString());
        editedMovie.setType("movie");
        editedMovie.setLastupdated(String.valueOf(LocalDateTime.now()));
        editedMovie.setTomatoes(editedTomato);
//        SpartaMongoDbFinalProjectApplication.logger.info("4: "+editedMovie.toString());

        movieRepository.save(editedMovie);

        return "/movies/movie-edit-success";
    }

    @GetMapping("/movies/home")
    public String moviesHome() {
        return "mainPages/movies-page";
    }
}
