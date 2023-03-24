package com.sparta.spartamongodbfinalproject.model.entities.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;

@Accessors(chain = true)
@Setter
@Getter
public class Imdb {/**/
//    imdb Object
//    rating 6.2
//    votes 1189
//    id 5

    //ToDo: Fix this, it should be a double




    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("votes")
    private Integer votes;

    @Field("id")
    @JsonProperty("Imdb_id")
    private Integer Imdb_id;

    @Override
    public String toString() {
        return "Imdb{" +
                "rating=" + rating +
                ", votes=" + votes +
                ", Imdb_id=" + Imdb_id +
                '}';
    }

}
