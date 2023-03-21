package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findCommentByNameEquals(String name);
}