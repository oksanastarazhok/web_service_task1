package apiTests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.CustomListener;
import utils.RestUtils;

import java.io.IOException;

@Listeners(CustomListener.class)
public class APITest {
    private String URLExpectedPositiveResponse = "http://services.groupkt.com/state/get/USA/ID";
    private String URLExpectedNegativeResponse = "http://services.groupkt.com/state/get/USA/Belarus";

    private Logger logger = Logger.getLogger(getClass());


    @Test(description = "Verify response is not null ")
    public void notEmptyHTTPResponseTest() throws IOException {
        logger.info( "Verify response is not null" );
        CloseableHttpResponse serverResponse = RestUtils.getResponse( URLExpectedPositiveResponse );
        Assert.assertNotNull( serverResponse );
    }

    @Test(description = "Verify response is 200 for countryCode = USA and valid stateCode=ID")
    public void positiveHTTPResponseStatusTest() throws IOException {
        logger.info("Verify response is 200 for countryCode = USA and valid stateCode=ID" );
        CloseableHttpResponse serverResponse = RestUtils.getResponse( URLExpectedPositiveResponse );
        Assert.assertEquals( serverResponse.getStatusLine().getStatusCode(), 200 );
    }

    @Test(description = "Verify capital is Boise for countryCode = USA and valid stateCode=ID")
    public void positiveCapitalTest() throws IOException {
        logger.info( "Verify capital is Boise for countryCode = USA and valid stateCode=ID" );
        CloseableHttpResponse serverResponse = RestUtils.getResponse( URLExpectedPositiveResponse );
        String responseString = RestUtils.getResponseAsString( serverResponse );
        boolean checkCapital = responseString.contains( "Boise" );
        Assert.assertTrue( checkCapital );
    }

    @Test(description = "Verify response is 200 for countryCode = USA and invalid stateCode = Belarus")
    public void negativeHTTPResponseStatusTest() throws IOException {
       logger.info( "Verify response is 200 for countryCode = USA and invalid stateCode = Belarus" );
        CloseableHttpResponse serverResponse = RestUtils.getResponse( URLExpectedNegativeResponse );
        Assert.assertEquals( serverResponse.getStatusLine().getStatusCode(), 200 );
    }

    @Test(description = "Verify body contains correct validation error")
    public void correctValidationErrorTest() throws IOException {
       logger.info( "Verify body contains correct validation error" );
        CloseableHttpResponse serverResponse = RestUtils.getResponse( URLExpectedNegativeResponse );
        String responseString = RestUtils.getResponseAsString( serverResponse );
        Boolean errorMsg = responseString.contains( "No matching state found for requested code" );
        Assert.assertTrue( errorMsg );
    }
}
