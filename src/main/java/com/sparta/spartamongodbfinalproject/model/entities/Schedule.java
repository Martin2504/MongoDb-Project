package com.sparta.spartamongodbfinalproject.model.entities;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDateTime;
import java.util.List;

@Document("schedule")
@Data
@Accessors(chain = true)
public class Schedule {

    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;

    @DocumentReference(collection = "movies")
    @Field("movie_id")
    private Movie movie;

    @DocumentReference(collection = "theaters")
    @Field("theater_id")
    private Theatre theatre;

    private List<LocalDateTime> startTime;



}
