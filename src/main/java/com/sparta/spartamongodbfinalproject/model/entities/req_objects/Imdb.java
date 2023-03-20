package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

public class Imdb {
    private int id;
    private double rating;
    private int votes;

    public Imdb(int id, double rating, int votes) {
        this.id = id;
        this.rating = rating;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
