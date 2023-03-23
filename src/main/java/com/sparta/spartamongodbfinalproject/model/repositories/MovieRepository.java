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

    @Query(value = "{'title': {'$regex' : ?0, '$options' : 'i'}}")
    List<Movie> findMovieByTitleEquals(String title);

}
