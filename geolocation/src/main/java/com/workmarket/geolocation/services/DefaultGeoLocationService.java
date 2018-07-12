package com.workmarket.geolocation.services;

import com.google.inject.Inject;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.workmarket.geolocation.domain.GeoCoordinates;
import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.domain.GeoLocationStatus;
import com.workmarket.geolocation.integrations.googlemaps.GoogleGeoCodingResponse;
import com.workmarket.geolocation.integrations.googlemaps.GoogleGeoLocationIntegration;

import java.io.IOException;

public class DefaultGeoLocationService implements GeoLocationService {

    private final GoogleGeoLocationIntegration googleGeoLocationIntegration;

    // Right now, only google is being used for geolocation.
    // Perhaps, in the future, the underlying geolocation service would be swapped out.
    // Or, we could use multiple geolocation services.
    // Therefore, the google geolocation integration is being used in this class via composition.

    @Inject
    public DefaultGeoLocationService(GoogleGeoLocationIntegration googleGeoLocationIntegration) {
        this.googleGeoLocationIntegration = googleGeoLocationIntegration;
    }

    @Override
    public GeoLocationResult getGeoLocation(String address) throws IOException {
        GoogleGeoCodingResponse response = googleGeoLocationIntegration.getGeoLocation(address);

        if(response.status.equals("OVER_QUERY_LIMIT")) {
            return new GeoLocationResult(address, GeoLocationStatus.NOT_FOUND, null);
        }

        Geometry geometry = response.getResult()[0].geometry;
        LatLng location = geometry.location;
        GeoCoordinates geoCoordinates = new GeoCoordinates(location.lat, location.lng);
        return new GeoLocationResult(address, GeoLocationStatus.FOUND, geoCoordinates);
    }
}
