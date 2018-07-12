package com.workmarket.geolocation.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.workmarket.LargeTests;
import com.workmarket.geolocation.domain.GeoCoordinates;
import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.domain.GeoLocationStatus;
import com.workmarket.geolocation.guice.GeoLocatorModule;
import com.workmarket.geolocation.sinks.TestSink;
import com.workmarket.geolocation.sources.FileSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Category(LargeTests.class)
@RunWith(MockitoJUnitRunner.class)
public class GeoLocatorTests {
    @Test
    public void findGeoLocations_withStubbedSourceAndSinks_succeeds() throws IOException {
        // Arrange
        final Injector injector = Guice.createInjector(new GeoLocatorModule());
        final GeoLocator geoLocator = injector.getInstance(GeoLocator.class);
        final TestSink testSink = new TestSink();
        final URL url = this.getClass().getResource("/test_addresses.txt");
        final FileSource source = new FileSource(url.getPath());

        // Act
        geoLocator.findGeoLocations(source, testSink);

        // Assert
        List<GeoLocationResult> results = testSink.getResults();
        GeoLocationResult firstResult = results.get(0);
        GeoLocationResult secondResult = results.get(1);

        Assert.assertEquals("777 Brockton Avenue, Abington MA 2351", firstResult.getAddress());
        Assert.assertEquals(GeoLocationStatus.FOUND, firstResult.getStatus());
        GeoCoordinates location = firstResult.getLocation();
        Assert.assertEquals(42.0963, location.getLat(), 0.0001);
        Assert.assertEquals(-70.968499999999995, location.getLon(), 0.0001);

        Assert.assertEquals("30 Memorial Drive, Avon MA 2322", secondResult.getAddress());
        Assert.assertEquals(GeoLocationStatus.FOUND, secondResult.getStatus());
        GeoCoordinates secondLocation = secondResult.getLocation();
        Assert.assertEquals(42.1210441, secondLocation.getLat(), 0.0001);
        Assert.assertEquals(-71.0300905, secondLocation.getLon(), 0.0001);
    }
}
