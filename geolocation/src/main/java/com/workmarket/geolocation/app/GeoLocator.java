package com.workmarket.geolocation.app;

import com.google.inject.Inject;
import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.services.GeoLocationService;
import com.workmarket.geolocation.sinks.Sink;
import com.workmarket.geolocation.sources.Source;

import java.io.IOException;

/*
 * This class can have source and any sink implementation passed to it.
 * I designed it this way so that the source and sink are swappable.
 * This allows us to easily change the input or output format.
 * The class could be even further generified by pulling out the GeoLocation Service.
 */
public class GeoLocator {
    private final GeoLocationService geoLocationService;

    @Inject
    public GeoLocator(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    public void findGeoLocations(Source<String> source, Sink<GeoLocationResult> sink) throws IOException {
        sink.open();
        while(source.hasNext()) {
            String address = source.getNext();

            if(!address.equals("")) {
                GeoLocationResult geoLocationResult = geoLocationService.getGeoLocation(address);
                sink.writeOne(geoLocationResult, source.hasNext());
            }
        }

        sink.close();
    }
}
