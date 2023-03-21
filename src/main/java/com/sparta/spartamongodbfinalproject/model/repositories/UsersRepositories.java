package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Users;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepositories extends MongoRepository<Users, ObjectId> {


    List<Users> findUsersByName(String name);

    Optional<Users> findById(ObjectId id);

    Optional<Users> deleteUsersByName(String name);
}
