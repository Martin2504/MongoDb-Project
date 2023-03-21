package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends MongoRepository<Theatre, String> {

    Optional<Theatre> findTheatreByTheatreId(Integer theatreId);

    @Query("{ 'location.address.city' : ?0 }")
    Optional<Theatre> findTheatresByCity(String city);
}
