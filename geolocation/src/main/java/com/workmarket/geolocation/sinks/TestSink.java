package com.workmarket.geolocation.sinks;

import com.workmarket.geolocation.domain.GeoLocationResult;

import java.util.LinkedList;
import java.util.List;

public class TestSink implements Sink<GeoLocationResult> {
    private final List<GeoLocationResult> results = new LinkedList<>();

    @Override
    public void writeOne(GeoLocationResult item, boolean b) {
        results.add(item);
    }

    @Override
    public void close() {

    }

    @Override
    public void open() {

    }

    public List<GeoLocationResult> getResults() {
        return results;
    }
}
