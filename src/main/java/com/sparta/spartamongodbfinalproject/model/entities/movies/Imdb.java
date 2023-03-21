package com.sparta.spartamongodbfinalproject.model.entities.movies;//package com.sparta.spartamongodbfinalproject.model.entities.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Imdb {
//    imdb Object
//    rating 6.2
//    votes 1189
//    id 5

    //ToDo: Fix this, it should be a double
    private double rating;

    private Integer votes;

    @Field("id")
    private Integer Imdb_id;
}
