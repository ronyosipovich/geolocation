package com.workmarket.geolocation.integrations.googlemaps;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.workmarket.geolocation.app.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleGeoLocationIntegration {
    private static final Logger logger = LoggerFactory.getLogger(GoogleGeoLocationIntegration.class);
    //todo: pull this out into a property.
    private final String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private final Gson gson = new Gson();
    private int MAXIMUM_ALLOWED_RETRIES;
    private final HttpRequestWrapper httpRequestWrapper;
    private final PropertiesLoader propertiesLoader;

    @Inject
    public GoogleGeoLocationIntegration(PropertiesLoader propertiesLoader, HttpRequestWrapper httpRequestWrapper) {
        this.propertiesLoader = propertiesLoader;
        this.httpRequestWrapper = httpRequestWrapper;
        MAXIMUM_ALLOWED_RETRIES = propertiesLoader.getPropertyAsInt("url.requests.max-retry", 5);
    }

    public GoogleGeoCodingResponse getGeoLocation(String address) throws IOException {
        logger.info("Getting geo-location for address {}", address);

        GoogleGeoCodingResponse apiResponse = null;

        int retryCounter = 0;
        while(retryCounter <= MAXIMUM_ALLOWED_RETRIES) {
            if(retryCounter == MAXIMUM_ALLOWED_RETRIES) {
                logger.info("Query rate limited for {}. No more retries left.", address);
                break;
            }

            apiResponse = doGetGeoLocation(address);

            boolean isQueryRateLimited = apiResponse.status.equals("OVER_QUERY_LIMIT");
            if(isQueryRateLimited && ++retryCounter > 0) {
                    logger.info("Query rate limited for {}. Attempting retry {} of {}", address, retryCounter, MAXIMUM_ALLOWED_RETRIES);

            } else if(!isQueryRateLimited) {
                logger.info("Found coordinates for address: {}", address);
                break;
            }
        }

        return apiResponse;

    }

    private GoogleGeoCodingResponse doGetGeoLocation(String address) throws IOException {
        URL url = new URL(this.url + URLEncoder.encode(address, "UTF-8"));
        String jsonText = httpRequestWrapper.get(url);
        return gson.fromJson(jsonText, GoogleGeoCodingResponse.class);
    }
}
