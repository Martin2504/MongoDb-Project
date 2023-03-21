package com.sparta.spartamongodbfinalproject.model.entities.req_objects;//package com.sparta.spartamongodbfinalproject.model.entities.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;

@Accessors(chain = true)
@NoArgsConstructor
@Setter
@Getter
public class Imdb {/**/
//    imdb Object
//    rating 6.2
//    votes 1189
//    id 5

    //ToDo: Fix this, it should be a double

    private Integer votes;
    private Double rating;
    @Field("id")
    private Integer id;
}
