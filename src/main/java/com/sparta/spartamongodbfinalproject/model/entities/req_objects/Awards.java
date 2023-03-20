package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Awards {
//    awards Object
//    wins 1
//    nominations 0
//    text "1 win."
    private Integer wins;
    private Integer nominations;
    private String text;

    @Override
    public String toString() {
        return "Awards{" +
                "wins=" + wins +
                ", nominations=" + nominations +
                ", text='" + text + '\'' +
                '}';
    }
}
