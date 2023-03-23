package com.sparta.spartamongodbfinalproject.model.entities;

import java.util.List;

public class CommentMovieSearcher {

    private Movie movie;
    private List<Comment> comments;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
