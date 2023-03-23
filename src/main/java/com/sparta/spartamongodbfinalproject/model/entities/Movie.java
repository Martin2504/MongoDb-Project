package com.sparta.spartamongodbfinalproject.model.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Award;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Imdb;
import com.sparta.spartamongodbfinalproject.model.entities.movies.Tomato;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Document("movies")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Movie {

    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String id;

    private String plot;

    private String poster;
    //    genres Array
    private List<String> genres;


//    runtime 1
    @JsonProperty("runtime")
    private Integer runtime;

    //cast Array
    private List<String> cast;

    //num_mflix_comments 0
    @JsonProperty("num_mflix_comment")
    private Integer num_mflix_comments;

    //    title"Blacksmith Scene"
    private String title;

    //fullplot"A stationary camera looks at a large anvil with a blacksmith behind it…"
    private String fullplot;

    //    countries Array
    private List<String> countries;

    //released 1893-05-09T00:00:00.000+00:00


//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime released;


    //directors Array
    private List<String> directors;

    //rated "UNRATED"
    private String rated;

    //awards Object
    private Award awards;

    //lastupdated : "2015-08-26 00:03:50.133000000"
    //pattern = "yyyy-MM-dd HH:mm:ss.SS"
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS'000000'")

    private String lastupdated;

    //year : 1893
    @JsonProperty("year")
    private Integer year;
    //    imdb :Object
    private Imdb imdb;

    //type "movie"
    private String type;

    //tomatoes : Object
    private Tomato tomatoes;


    private List<String> languages;
    private List<String> writers;

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", plot='" + plot + '\'' +
                ", genres=" + genres +
                ", runtime=" + runtime +
                ", cast=" + cast +
                ", num_mflix_comments=" + num_mflix_comments +
                ", title='" + title + '\'' +
                ", fullplot='" + fullplot + '\'' +
                ", countries=" + countries +
                ", released=" + released +
                ", directors=" + directors +
                ", rated='" + rated + '\'' +
                ", awards=" + awards +
                ", lastupdated='" + lastupdated + '\'' +
                ", year='" + year + '\'' +
                ", imdb=" + imdb +
                ", type='" + type + '\'' +
                ", tomatoes=" + tomatoes +
                '}';
    }

}


