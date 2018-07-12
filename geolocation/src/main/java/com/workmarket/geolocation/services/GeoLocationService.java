package com.workmarket.geolocation.services;

import com.workmarket.geolocation.domain.GeoLocationResult;

import java.io.IOException;

public interface GeoLocationService {
    GeoLocationResult getGeoLocation(String address) throws IOException;
}
