package com.sparta.spartamongodbfinalproject.model.entities.movies;

import com.sparta.spartamongodbfinalproject.model.entities.movies.Critic;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Viewer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Setter
@Getter
public class Tomato {

    //    tomatoes : Object
//    viewer : Object
//    lastUpdated : 2015-06-28T18:34:09.000+00:00
    private Viewer viewer;
    private String consensus;
    private Critic critic;
    private Date dvd;
    private Integer fresh;
    private Date tomato_lastUpdated;
    private String production;
    private Integer rotten;
    private String website;

}



