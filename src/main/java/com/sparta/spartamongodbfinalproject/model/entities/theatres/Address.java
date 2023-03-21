package com.sparta.spartamongodbfinalproject.model.entities.theatres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class Address {
    //    street1 "390 Northridge Mall"
//    city "Salinas"
//    state "CA"
//    zipcode "93906"
    private String street1;

    private String street2;

    private String city;

    private String state;

    private String zipcode;

    @Override
    public String toString() {
        return "Address{" +
                "street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
