package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;
public interface CommentRepository extends MongoRepository<Comment, String> {


    @Query(value = "{'name': {'$regex' : ?0, '$options' : 'i'}}")
    List<Comment> findCommentByNameEquals(String name);

    List<Comment> findCommentByMovie_Id(String id);
}

