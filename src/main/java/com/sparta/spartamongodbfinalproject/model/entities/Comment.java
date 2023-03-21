package com.sparta.spartamongodbfinalproject.model.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

@Document("comments")
@Data
@Accessors(chain = true)
public class Comment {
    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;
    private String name;
    private String email;
    @DocumentReference(collection = "movies")
    @Field("movie_id")
    private Movie movie;
    private String text;
    private String date;
}


