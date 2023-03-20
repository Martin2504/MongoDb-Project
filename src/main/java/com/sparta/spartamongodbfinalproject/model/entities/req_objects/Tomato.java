package com.sparta.spartamongodbfinalproject.model.entities.req_objects;//package com.sparta.spartamongodbfinalproject.model.entities.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@Setter
@Getter
public class Tomato {
    // tomatoes : Object
    // viewer : Object
    // fresh: 6
    // critic: Object
    // lastUpdated : 2015-06-28T18:34:09.000+00:00
    private Viewer viewer;
    private Critic critic;
    private LocalDate lastUpdated;
    private Integer rotten;
    private Integer fresh;

}

