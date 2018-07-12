package com.workmarket.geolocation.sinks;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.mappers.GeoLocationResultMapper;

/*
 * This sink was included because the README.md mentioned that the "The application should output a JSONArray of JSONObjects."
 * It will work well with small address input being passed to the Source.
 * However, it will not scale for very large files. For those, it will be better to specify StandardOutputJsonSink when running the console application.
 */
public class JsonArraySink implements Sink<GeoLocationResult> {

    private final JsonArray jsonArray = new JsonArray();

    @Override
    public void writeOne(GeoLocationResult item, boolean hasNext) {
        JsonObject jsonObject = GeoLocationResultMapper.map(item);
        jsonArray.add(jsonObject);
    }

    @Override
    public void open() {
        //No op
    }

    public void close() {
        System.out.print(jsonArray.toString());
    }

}
