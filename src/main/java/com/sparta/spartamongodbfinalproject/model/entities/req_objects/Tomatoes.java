package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Viewer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Tomatoes {
//    tomatoes : Object
//    viewer : Object
//    lastUpdated : 2015-06-28T18:34:09.000+00:00
    private Viewer viewer;
    private Date lastUpdated;

    @Override
    public String toString() {
        return "Tomatoes{" +
                "viewer=" + viewer +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
