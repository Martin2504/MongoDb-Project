package com.sparta.spartamongodbfinalproject.model.entities;

import com.sparta.spartamongodbfinalproject.model.entities.theatres.Location;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "theaters")
public class Theater {
    private Location location;
    private int theaterId;
    private Object _id;

    public Theater(Object _id, int theaterId , Location location) {
        this.location = location;
        this.theaterId = theaterId;
        this._id = _id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Theaters{" +
                "location=" + location +
                ", theaterId=" + theaterId +
                ", _id=" + _id +
                '}';
    }
}
