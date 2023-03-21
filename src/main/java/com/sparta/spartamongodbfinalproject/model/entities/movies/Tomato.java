package com.sparta.spartamongodbfinalproject.model.entities.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Tomato {
//    tomatoes : Object
//    viewer : Object
//    lastUpdated : 2015-06-28T18:34:09.000+00:00
    private Viewer viewer;
    private Date lastUpdated;
}
