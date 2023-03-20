package com.sparta.spartamongodbfinalproject.model.entities.req_objects;

public class Location {
    private Geo geo;
    private Address address;

    public Location(Geo geo, Address address) {
        this.geo = geo;
        this.address = address;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "geo=" + geo +
                ", address=" + address +
                '}';
    }
}
