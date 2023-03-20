package com.sparta.spartamongodbfinalproject.model.entities;


import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Award;
import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Imdb;
import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Tomato;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document("movies")
@Data
@Accessors(chain = true)
public class Movies {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String plot;

    //    genres Array
    private List<String> genres;

    //    runtime 1
    private Integer runtime;

    //cast Array
    private List<String> cast;

    //num_mflix_comments 0
    private Integer num_mflix_comments;

    //    title"Blacksmith Scene"
    private String title;

    //fullplot"A stationary camera looks at a large anvil with a blacksmith behind it…"
    private String fullplot;

    //    countries Array
    private List<String> countries;

    //released 1893-05-09T00:00:00.000+00:00
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String released;

    //directors Array
    private List<String> directors;

    //rated "UNRATED"
    private String rated;

    //awards Object
    private Award awards;

    //lastupdated : "2015-08-26 00:03:50.133000000"
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private String lastupdated;

    //year : 1893
    private String year;
    //    imdb :Object
    private Imdb imdb;

    //type "movie"
    private String type;

    //tomatoes : Object
    private Tomato tomatoes;
}