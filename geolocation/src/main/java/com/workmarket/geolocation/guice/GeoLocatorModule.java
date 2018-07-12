package com.workmarket.geolocation.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.workmarket.geolocation.app.GeoLocator;
import com.workmarket.geolocation.app.PropertiesLoader;
import com.workmarket.geolocation.services.DefaultGeoLocationService;
import com.workmarket.geolocation.services.GeoLocationService;

public class GeoLocatorModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(GeoLocator.class);
        binder.bind(GeoLocationService.class).to(DefaultGeoLocationService.class).in(Singleton.class);
        binder.bind(PropertiesLoader.class).in(Singleton.class);
    }
}
