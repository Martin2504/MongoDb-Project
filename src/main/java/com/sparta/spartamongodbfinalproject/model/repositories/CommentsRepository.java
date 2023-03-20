package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Comments;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comments, ObjectId> {
    List<Comments> findCommentsByNameEquals(String name);
}
