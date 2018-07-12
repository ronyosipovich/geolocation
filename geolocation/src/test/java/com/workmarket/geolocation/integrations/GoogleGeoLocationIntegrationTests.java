package com.workmarket.geolocation.integrations;

import com.google.maps.model.Geometry;
import com.workmarket.SmallTests;
import com.workmarket.geolocation.app.PropertiesLoader;
import com.workmarket.geolocation.integrations.googlemaps.GoogleGeoCodingResponse;
import com.workmarket.geolocation.integrations.googlemaps.GoogleGeoLocationIntegration;
import com.workmarket.geolocation.integrations.googlemaps.HttpRequestWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@Category(SmallTests.class)
@RunWith(MockitoJUnitRunner.class)
public class GoogleGeoLocationIntegrationTests {

    @Mock
    private HttpRequestWrapper httpRequestWrapper;

    private GoogleGeoLocationIntegration target;

    private final PropertiesLoader propertiesLoader = new PropertiesLoader();

    @Before
    public void setup() {
        target = new GoogleGeoLocationIntegration(propertiesLoader, httpRequestWrapper);
    }


    //todo: fix this test
//    @Test
//    public void getGeoLocation_emptyStringPrividedAsParam_returnsException() throws Exception {
//        // Arrange
//        when(httpRequestWrapper.get(any())).thenReturn("");
//
//        // Act
//        GoogleGeoCodingResponse response = target.getGeoLocation("");
//
//        // Assert
//        Geometry geometry = response.getResult()[0].geometry;
//        Assert.assertEquals(42.0963, geometry.location.lat, 0.00001);
//        Assert.assertEquals(-70.96849999999999, geometry.location.lng, 0.00001);
//    }

    @Test
    public void getGeoLocation_addressProvided_returnsCoordinateInGoogleGeoCodingResponse() throws Exception {
        // Arrange
        when(httpRequestWrapper.get(any())).thenReturn(addressResponse);

        // Act
        GoogleGeoCodingResponse response = target.getGeoLocation("777 Brockton Avenue, Abington MA 2351");

        // Assert
        Geometry geometry = response.getResult()[0].geometry;
        Assert.assertEquals(42.0963, geometry.location.lat, 0.00001);
        Assert.assertEquals(-70.96849999999999, geometry.location.lng, 0.00001);
    }

    @Test
    public void getGeoLocation_queryOverLimitFiveTimes_exitsLoopWithCorrectStatusOnResponse() throws Exception {
        // Arrange
        when(httpRequestWrapper.get(any())).thenReturn(queryOverLimitResponse);

        // Act
        GoogleGeoCodingResponse response = target.getGeoLocation("777 Brockton Avenue, Abington MA 2351");

        // Assert
        Assert.assertEquals("OVER_QUERY_LIMIT", response.status);
    }

    private String queryOverLimitResponse = "{" +
            "\"error_message\": \"You have exceeded your daily request quota for this API. We recommend registering for a key at the Google Developers Console: https://console.developers.google.com/apis/credentials?project=_\"," +
            "\"results\": []," +
            "\"status\": \"OVER_QUERY_LIMIT\"" +
            "}";


    private String addressResponse = "  {" +
            "            \"results\": [{" +
            "                \"address_components\": [{" +
            "                    \"long_name\": \"777\"," +
            "                    \"short_name\": \"777\"," +
            "                    \"types\": [\"street_number\"]" +
            "                }, {" +
            "                    \"long_name\": \"Brockton Avenue\"," +
            "                    \"short_name\": \"Brockton Ave\"," +
            "                    \"types\": [\"route\"]" +
            "                }, {" +
            "                    \"long_name\": \"Abington\"," +
            "                    \"short_name\": \"Abington\"," +
            "                    \"types\": [\"locality\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"Plymouth County\"," +
            "                    \"short_name\": \"Plymouth County\"," +
            "                    \"types\": [\"administrative_area_level_2\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"Massachusetts\"," +
            "                    \"short_name\": \"MA\"," +
            "                    \"types\": [\"administrative_area_level_1\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"United States\"," +
            "                    \"short_name\": \"US\"," +
            "                    \"types\": [\"country\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"02351\"," +
            "                    \"short_name\": \"02351\"," +
            "                    \"types\": [\"postal_code\"]" +
            "                }]," +
            "                \"formatted_address\": \"777 Brockton Ave, Abington, MA 02351, USA\"," +
            "                \"geometry\": {" +
            "                    \"location\": {" +
            "                        \"lat\": 42.0963," +
            "                        \"lng\": -70.96849999999999" +
            "                    }," +
            "                    \"location_type\": \"ROOFTOP\"," +
            "                    \"viewport\": {" +
            "                        \"northeast\": {" +
            "                            \"lat\": 42.0976489802915," +
            "                            \"lng\": -70.9671510197085" +
            "                        }," +
            "                        \"southwest\": {" +
            "                            \"lat\": 42.0949510197085," +
            "                            \"lng\": -70.9698489802915" +
            "                        }" +
            "                    }" +
            "                }," +
            "                \"place_id\": \"ChIJTxHf2G2b5IkR2BgSaknj3RU\"," +
            "                \"types\": [\"department_store\", \"establishment\", \"food\", \"grocery_or_supermarket\", \"point_of_interest\", \"store\", \"supermarket\"]" +
            "            }, {" +
            "                \"address_components\": [{" +
            "                    \"long_name\": \"777\"," +
            "                    \"short_name\": \"777\"," +
            "                    \"types\": [\"street_number\"]" +
            "                }, {" +
            "                    \"long_name\": \"Brockton Avenue\"," +
            "                    \"short_name\": \"Brockton Ave\"," +
            "                    \"types\": [\"route\"]" +
            "                }, {" +
            "                    \"long_name\": \"Abington\"," +
            "                    \"short_name\": \"Abington\"," +
            "                    \"types\": [\"locality\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"Plymouth County\"," +
            "                    \"short_name\": \"Plymouth County\"," +
            "                    \"types\": [\"administrative_area_level_2\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"Massachusetts\"," +
            "                    \"short_name\": \"MA\"," +
            "                    \"types\": [\"administrative_area_level_1\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"United States\"," +
            "                    \"short_name\": \"US\"," +
            "                    \"types\": [\"country\", \"political\"]" +
            "                }, {" +
            "                    \"long_name\": \"02351\"," +
            "                    \"short_name\": \"02351\"," +
            "                    \"types\": [\"postal_code\"]" +
            "                }, {" +
            "                    \"long_name\": \"2142\"," +
            "                    \"short_name\": \"2142\"," +
            "                    \"types\": [\"postal_code_suffix\"]" +
            "                }]," +
            "                \"formatted_address\": \"777 Brockton Ave, Abington, MA 02351, USA\"," +
            "                \"geometry\": {" +
            "                    \"location\": {" +
            "                        \"lat\": 42.096253," +
            "                        \"lng\": -70.96862" +
            "                    }," +
            "                    \"location_type\": \"ROOFTOP\"," +
            "                    \"viewport\": {" +
            "                        \"northeast\": {" +
            "                            \"lat\": 42.09760198029149," +
            "                            \"lng\": -70.96727101970849" +
            "                        }," +
            "                        \"southwest\": {" +
            "                            \"lat\": 42.09490401970849," +
            "                            \"lng\": -70.96996898029151" +
            "                        }" +
            "                    }" +
            "                }," +
            "                \"place_id\": \"ChIJG7JOTG6b5IkReiJCMPaFw8I\"," +
            "                \"types\": [\"establishment\", \"finance\", \"point_of_interest\"]" +
            "            }]," +
            "            \"status\": \"OK\"" +
            "        }";


}
