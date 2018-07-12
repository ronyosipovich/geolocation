package com.workmarket.geolocation.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.guice.GeoLocatorModule;
import com.workmarket.geolocation.sinks.JsonArraySink;
import com.workmarket.geolocation.sinks.Sink;
import com.workmarket.geolocation.sinks.StandardOutputJsonSink;
import com.workmarket.geolocation.sources.FileSource;
import com.workmarket.geolocation.sources.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GeoLocationConsoleApp {
    private static final Logger logger = LoggerFactory.getLogger(GeoLocationConsoleApp.class);

    public static void main(String[] args) throws IOException {
        if(args.length < 3) {
            System.out.println("Usage: [file path] [SourceName] [SinkName]");
            return;
        }

        String path = args[0];
        String sourceName = args[1];
        String sinkName = args[2];

        Source<String> source = null;
        Sink<GeoLocationResult> sink = null;

        // Since the source and sink exist in specific packages in this project,
        // I could have technically used reflection here to get the classes from the arguments provided.
        if(sourceName.equals("FileSource")) {
            source = new FileSource(path);
        }

        if(sinkName.equals("StandardOutputJsonSink")) {
            sink = new StandardOutputJsonSink();
        } else if(sinkName.equals("JsonArraySink")) {
            sink = new JsonArraySink();
        }

        if(source == null) {
            System.out.println("Unable to find Source for value provided: " + sourceName);
            return;
        }

        if(sink == null) {
            System.out.println("Unable to find Sink for value provided: " + sinkName);
            return;
        }

        Injector injector = Guice.createInjector(new GeoLocatorModule());
        GeoLocator geoLocator = injector.getInstance(GeoLocator.class);


        try {
            geoLocator.findGeoLocations(source, sink);
        } catch(Exception e) {
            System.out.println("Error encountered. Please check log file.");
            logger.info("Error when running application" , e);

        }
    }

}
