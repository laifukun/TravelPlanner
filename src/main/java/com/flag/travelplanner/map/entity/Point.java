package com.flag.travelplanner.map.entity;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = -3218892984136950243L;
    private double lat;
    private double lng;

    public Point() {}
    public Point(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
