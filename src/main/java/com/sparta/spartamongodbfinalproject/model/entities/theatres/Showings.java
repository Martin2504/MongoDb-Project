package com.sparta.spartamongodbfinalproject.model.entities.theatres;

import com.sparta.spartamongodbfinalproject.model.entities.Movie;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Optional;

@Accessors(chain = true)
@Setter
@Getter
@Data
public class Showings {
    @DocumentReference(collection = "movies")
    @Field("movie_id")
    private Movie movie;

    @DocumentReference(collection = "theaters")
    @Field("theater_id")
    private Theatre theatre;

    private LocalDateTime start_time;
    private String startTimeString;
}

