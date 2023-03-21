package com.sparta.spartamongodbfinalproject.model.entities.movies;

//package com.sparta.spartamongodbfinalproject.model.entities.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Award {
    //    awards Object
//    wins 1
//    nominations 0
//    text "1 win."
    private Integer wins;
    private Integer nominations;
    private String text;
}

