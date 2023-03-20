package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

import java.time.LocalDateTime;

public class Tomatoes {
    private Viewer viewer;
    private LocalDateTime lastUpdated;

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static class Viewer {
        private int rating;
        private int numReviews;
        private int meter;


        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getNumReviews() {
            return numReviews;
        }

        public void setNumReviews(int numReviews) {
            this.numReviews = numReviews;
        }

        public int getMeter() {
            return meter;
        }

        public void setMeter(int meter) {
            this.meter = meter;
        }
    }

}
