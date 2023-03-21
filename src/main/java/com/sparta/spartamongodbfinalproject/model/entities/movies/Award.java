package com.sparta.spartamongodbfinalproject.model.entities.movies;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
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

