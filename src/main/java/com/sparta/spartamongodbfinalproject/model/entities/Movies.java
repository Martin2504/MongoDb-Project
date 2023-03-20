package com.sparta.spartamongodbfinalproject.model.entities;

import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Awards;
import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Imdb;
import com.sparta.spartamongodbfinalproject.model.entities.req_objects.Tomatoes;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Arrays;

@Document(collection = "movies")
public class Movies {

    private ObjectId _id;
    private String plot;
    private String[] genres;
    private Integer runtime;
    private String[] cast;
    private Integer num_mflix_comments;
    private String title;
    private String fullPlot;
    private String[] countries;
    private LocalDateTime released;
    private String[] directors;
    private String rated;
    private Awards awards;
    private LocalDateTime lastUpdated;
    private Integer year;
    private Imdb imdb;
    private String type;
    private Tomatoes tomatoes;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public Integer getNum_mflix_comments() {
        return num_mflix_comments;
    }

    public void setNum_mflix_comments(Integer num_mflix_comments) {
        this.num_mflix_comments = num_mflix_comments;
    }

    public String getTitle() {
        return title;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    public void setImdb(Imdb imdb) {
        this.imdb = imdb;
    }

    public Tomatoes getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(Tomatoes tomatoes) {
        this.tomatoes = tomatoes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullPlot() {
        return fullPlot;
    }

    public void setFullPlot(String fullPlot) {
        this.fullPlot = fullPlot;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public LocalDateTime getReleased() {
        return released;
    }

    public void setReleased(LocalDateTime released) {
        this.released = released;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Object getAwards() {
        return awards;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Object getImdb() {
        return imdb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Movies{" +
                "_id=" + _id +
                ", plot='" + plot + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", runtime=" + runtime +
                ", cast=" + Arrays.toString(cast) +
                ", num_mflix_comments=" + num_mflix_comments +
                ", title='" + title + '\'' +
                ", fullPlot='" + fullPlot + '\'' +
                ", countries=" + Arrays.toString(countries) +
                ", released=" + released +
                ", directors=" + Arrays.toString(directors) +
                ", rated='" + rated + '\'' +
                ", awards=" + awards +
                ", lastUpdated=" + lastUpdated +
                ", year=" + year +
                ", imdb=" + imdb +
                ", type='" + type + '\'' +
                ", tomatoes='" + tomatoes + '\'' +
                '}';
    }
}
