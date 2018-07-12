package com.workmarket.geolocation.integrations.googlemaps;

import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.GeocodingResult;

public class GoogleGeoCodingResponse implements ApiResponse<GeocodingResult[]> {
    public String status;
    public String errorMessage;
    public GeocodingResult[] results;

    @Override
    public boolean successful() {
        return "OK".equals(status) || "ZERO_RESULTS".equals(status);
    }

    @Override
    public GeocodingResult[] getResult() {
        return results;
    }

    @Override
    public ApiException getError() {
        if (successful()) {
            return null;
        }
        return ApiException.from(status, errorMessage);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public GeocodingResult[] getResults() {
        return results;
    }

    public void setResults(GeocodingResult[] results) {
        this.results = results;
    }
}
