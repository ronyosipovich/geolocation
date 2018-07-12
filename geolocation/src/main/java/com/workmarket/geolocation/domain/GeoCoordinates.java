package com.workmarket.geolocation.domain;

public class GeoCoordinates {
    private final double lat;
    private final double lon;

    public GeoCoordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
