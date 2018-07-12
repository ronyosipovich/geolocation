package com.workmarket.geolocation.domain;

public class GeoLocationResult {
    private final String address;
    private final GeoLocationStatus status;
    private final GeoCoordinates location;

    public GeoLocationResult(String address, GeoLocationStatus status, GeoCoordinates location) {
        this.address = address;
        this.status = status;
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public GeoLocationStatus getStatus() {
        return status;
    }

    public GeoCoordinates getLocation() {
        return location;
    }
}
