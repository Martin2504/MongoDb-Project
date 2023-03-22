package com.sparta.spartamongodbfinalproject.model.entities.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

@Accessors(chain = true)
@Setter
@Getter
public class Award {
//    awards Object
//    wins 1
//    nominations 0
//    text "1 win."
    @JsonProperty("wins")
    private Integer wins;
    @JsonProperty("nominations")
    private Integer nominations;
    private String text;
}
