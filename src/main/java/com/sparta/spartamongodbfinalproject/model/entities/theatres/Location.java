package com.sparta.spartamongodbfinalproject.model.entities.theatres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class Location {
    private Address address;
    private Geo geo;

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                ", geo=" + geo +
                '}';
    }
}
