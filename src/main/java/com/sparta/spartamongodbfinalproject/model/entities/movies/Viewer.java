package com.sparta.spartamongodbfinalproject.model.entities.movies;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class Viewer {
    //    viewer : Object
//    rating : 3
//    numReviews : 184
//    meter : 32

    private Double viewer_rating;
    private Integer viewer_numReviews;
    private Integer viewer_meter;

    @Override
    public String toString() {
        return "Viewer{" +
                "viewer_rating=" + viewer_rating +
                ", viewer_numReviews=" + viewer_numReviews +
                ", viewer_meter=" + viewer_meter +
                '}';
    }
}
