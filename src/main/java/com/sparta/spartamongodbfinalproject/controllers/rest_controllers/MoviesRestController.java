package com.sparta.spartamongodbfinalproject.controllers.rest_controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MoviesRestController {
    private final MovieRepository moviesRepository;
    private final ObjectMapper mapper;
    ResponseEntity<String> response;

    public MoviesRestController(MovieRepository moviesRepository, ObjectMapper mapper) {
        this.moviesRepository = moviesRepository;
        this.mapper = mapper;
    }

    @GetMapping(value = "api/movies")
    public ResponseEntity<String> getAllMovies(){
        List<Movie> movies = moviesRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movies!=null){
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(movies),httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("{\"message:\":\"Unable to connect to the database, please try again later\"}",httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }
    @GetMapping(value = "api/movie/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable String id){
        Optional<Movie> movie = moviesRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movie!=null){
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(mapper.writeValueAsString(movie),httpHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("{\"message:\":\"Unable to connect to the database, please try again later\"}",httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }
    @DeleteMapping(value = "api/movie/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String id){
        Optional<Movie> movie = moviesRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(movie!=null){
            try{ response = new ResponseEntity<>(mapper.writeValueAsString(movie.get().getTitle() + " will be updated"), httpHeaders, HttpStatus.OK);
            }catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
                moviesRepository.deleteById(id);
            return response;
        }
        ResponseEntity<String> moviesNotFoundException = new ResponseEntity<>("{\"message:\":\"Unable to connect to the database, please try again later\"}",httpHeaders, HttpStatus.NOT_FOUND);
        return moviesNotFoundException;
    }
    @PutMapping(value = "api/movie/edit/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable String id, @RequestBody Movie movie){
    Optional<Movie> updatedMovie = moviesRepository.findById(id);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("content-type", "application/json");
    if(updatedMovie.isPresent()){
        try{ response = new ResponseEntity<>(mapper.writeValueAsString(updatedMovie.get().getTitle() + " will be updated"), httpHeaders, HttpStatus.OK);
    }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
            updatedMovie.get().setTitle(movie.getTitle());
        updatedMovie.get().setAwards(movie.getAwards());
        updatedMovie.get().setGenres(movie.getGenres());
        updatedMovie.get().setCast(movie.getCast());
        updatedMovie.get().setImdb(movie.getImdb());
        updatedMovie.get().setDirectors(movie.getDirectors());
        updatedMovie.get().setCountries(movie.getCountries());
        updatedMovie.get().setPlot(movie.getPlot());
        updatedMovie.get().setRated(movie.getRated());
        updatedMovie.get().setReleased(movie.getReleased());
        updatedMovie.get().setType(movie.getType());
        updatedMovie.get().setFullplot(movie.getFullplot());
        updatedMovie.get().setNum_mflix_comments(movie.getNum_mflix_comments());
        updatedMovie.get().setLastupdated(movie.getLastupdated());
        updatedMovie.get().setRuntime(movie.getRuntime());
        updatedMovie.get().setTomatoes(movie.getTomatoes());
        moviesRepository.save(updatedMovie.get());
        return response;
    }
    moviesRepository.save(movie);
        try {
            return new ResponseEntity<>(mapper.writeValueAsString("New movie will be added"), httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "api/movie/create")
    public ResponseEntity<String> CreateMovie(@RequestBody Movie movie){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("content-type", "application/json");
    moviesRepository.save(movie);
        try {
            return new ResponseEntity<>(mapper.writeValueAsString("New movie will be added"), httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
