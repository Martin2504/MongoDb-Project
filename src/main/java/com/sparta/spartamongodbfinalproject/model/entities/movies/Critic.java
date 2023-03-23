package com.sparta.spartamongodbfinalproject.model.entities.movies;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter

public class Critic {
    private Double critic_rating;
    private Integer critic_numReviews;
    private Integer critic_meter;

    @Override
    public String toString() {
        return "Critic{" +
                "critic_rating=" + critic_rating +
                ", critic_numReviews=" + critic_numReviews +
                ", critic_meter=" + critic_meter +
                '}';
    }
}
