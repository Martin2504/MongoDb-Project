package com.sparta.spartamongodbfinalproject.model.entities.theatres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Location {
    private Address address;
    private Geo geo;
}
