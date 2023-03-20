package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@Setter
@Getter
public class Critic {

    // Critic Object
    // meter: 96
    // numReviews: 2100
    // rating: 9.6

    private Integer meter;
    private Integer numReviews;
    private Double rating;
}
