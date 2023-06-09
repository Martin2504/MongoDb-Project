package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByTitle(String title);
    Movie findMoviesById(String id);
    List<Movie> findMoviesByTitle(String title);
    @Query(value = "{'title': {'$regex' : ?0, '$options' : 'i'}}")
    List<Movie> findMovieByTitleEquals(String title);


    Movie findMovieByTitle(String title);




    @Query(value = "{ 'title' : ?0 }")
    List<Movie> findMovieByTitleAndYear(String title);

}

