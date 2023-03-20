package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

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
    private String rating;

    private Integer votes;

    @Field("id")
    private Integer id;

    @Override
    public String toString() {
        return "Imdb{" +
                "rating='" + rating + '\'' +
                ", votes=" + votes +
                ", id=" + id +
                '}';
    }
}
