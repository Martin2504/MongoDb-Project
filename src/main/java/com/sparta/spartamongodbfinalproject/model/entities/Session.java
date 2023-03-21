package com.sparta.spartamongodbfinalproject.model.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sessions")
public class Session {
    private ObjectId _id;
    private String user_id;
    private String jwt;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "_id=" + _id +
                ", user_id='" + user_id + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}