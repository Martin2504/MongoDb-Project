package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UsersRepository extends MongoRepository<Users, ObjectId> {

    @Query("{ 'name' : ?0 }")
    List<Users> findByName(String name);

    Users findUsersByName(String name);
}
