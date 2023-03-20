package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Movies;
import com.sparta.spartamongodbfinalproject.model.repositories.MoviesRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MoviesRestController {
    private final MoviesRepository moviesRepository;
    private final ObjectMapper mapper;


    public MoviesRestController(MoviesRepository moviesRepository, ObjectMapper mapper) {
        this.moviesRepository = moviesRepository;
        this.mapper = mapper;
    }

    @GetMapping(value = "api/movies")
    public ResponseEntity<String> getAllMovies(){
        List<Movies> movies = moviesRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movies!=null){
            ResponseEntity<String> response = new ResponseEntity<>(movies.toString(),httpHeaders, HttpStatus.OK);
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("{\"message:\":\"Unable to connect to the database, please try again later\"}",httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }
    @GetMapping(value = "api/movie/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable ObjectId id){
        Optional<Movies> movie = moviesRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movie!=null){
            ResponseEntity<String> response = new ResponseEntity<>(movie.toString(),httpHeaders, HttpStatus.OK);
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("{\"message:\":\"Unable to connect to the database, please try again later\"}",httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }
    @DeleteMapping(value = "api/movie/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable ObjectId id){
        Optional<Movies> movie = moviesRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movie!=null){
            ResponseEntity<String> response = new ResponseEntity<>(movie.get().getTitle() + " will be deleted", httpHeaders, HttpStatus.OK);
            moviesRepository.deleteById(id);
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("{\"message:\":\"Unable to connect to the database, please try again later\"}",httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }
    @PostMapping(value = "api/movie/edit/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable ObjectId id, @RequestBody Movies movie){
    return null;
    }
}
