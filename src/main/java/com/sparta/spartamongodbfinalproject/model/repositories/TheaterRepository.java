package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Theater;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface TheaterRepository extends MongoRepository<Theatre, String> {
}
