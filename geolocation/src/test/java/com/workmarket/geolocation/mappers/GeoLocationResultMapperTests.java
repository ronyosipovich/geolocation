package com.workmarket.geolocation.mappers;

import com.google.gson.JsonObject;
import com.workmarket.SmallTests;
import com.workmarket.geolocation.domain.GeoCoordinates;
import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.domain.GeoLocationStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@Category(SmallTests.class)
@RunWith(MockitoJUnitRunner.class)
public class GeoLocationResultMapperTests {

    @Test
    public void map_geoLocationResultFullyPopulated_correctlyPopulatesJsonObject() {
        // Arrange
        GeoCoordinates location = new GeoCoordinates(42.0963, -70.96849999999999);
        GeoLocationResult result = new GeoLocationResult("777 Brockton Avenue, Abington MA 2351", GeoLocationStatus.FOUND, location);

        // Act
        JsonObject map = GeoLocationResultMapper.map(result);
        // Assert
        String expected = "{\"address\":\"777 Brockton Avenue, Abington MA 2351\",\"status\":\"FOUND\",\"location\":{\"lat\":42.0963,\"lon\":-70.96849999999999}}";
        Assert.assertEquals(expected, map.toString());
    }

    @Test
    public void map_geoLocationResultNotFound_correctlyPopulatesJsonObject() {
        // Arrange
        GeoLocationResult result = new GeoLocationResult("777 Brockton Avenue, Abington MA 2351", GeoLocationStatus.NOT_FOUND, null);

        // Act
        JsonObject map = GeoLocationResultMapper.map(result);
        // Assert
        String expected = "{\"address\":\"777 Brockton Avenue, Abington MA 2351\",\"status\":\"NOT_FOUND\",\"location\":null}";
        Assert.assertEquals(expected, map.toString());
    }
}
