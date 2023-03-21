package com.sparta.spartamongodbfinalproject.model.entities.theatres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Setter
@Getter
public class Geo {
//    type "Point"
//    coordinates Array
//    0 : -121.65946
//            1 : 36.715809
    private String type;

    private List<Double> coordinates;
}
