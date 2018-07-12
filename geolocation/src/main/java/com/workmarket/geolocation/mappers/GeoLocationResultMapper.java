package com.workmarket.geolocation.mappers;

import com.google.gson.JsonObject;
import com.workmarket.geolocation.domain.GeoCoordinates;
import com.workmarket.geolocation.domain.GeoLocationResult;

public class GeoLocationResultMapper {
    public static JsonObject map(GeoLocationResult geoLocationResult) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("address",geoLocationResult.getAddress());
        jsonObject.addProperty("status", geoLocationResult.getStatus().toString());

        GeoCoordinates resultLocation = geoLocationResult.getLocation();

        if(resultLocation != null) {
            JsonObject location = new JsonObject();
            location.addProperty("lat", resultLocation.getLat());
            location.addProperty("lon", resultLocation.getLon());
            jsonObject.add("location", location);
        } else {
            jsonObject.add("location", null);
        }

        return jsonObject;
    }
}
